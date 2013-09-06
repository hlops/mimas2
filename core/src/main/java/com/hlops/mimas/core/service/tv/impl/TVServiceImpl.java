package com.hlops.mimas.core.service.tv.impl;

import com.hlops.mimas.core.config.Mimas;
import com.hlops.mimas.core.config.MimasConfig;
import com.hlops.mimas.core.data.bean.tv.TeleProgramItem;
import com.hlops.mimas.core.service.tv.TVService;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 09.05.13
 * Time: 20:26
 * To change this template use File | Settings | File Templates.
 */
public class TVServiceImpl implements TVService {

    private static final long DIFF_IN_MILLIS = 11644473600000L;

    @Override
    public List<TeleProgramItem> load(InputStream ndxStream, InputStream pdtStream) throws IOException {

        List<TeleProgramItem> list = new ArrayList<TeleProgramItem>();

        byte[] buffer = new byte[128];
        ByteBuffer shortBuffer = ByteBuffer.wrap(buffer, 0, 2).order(ByteOrder.LITTLE_ENDIAN);
        ByteBuffer intBuffer = ByteBuffer.wrap(buffer, 0, 4).order(ByteOrder.LITTLE_ENDIAN);
        ByteBuffer longBuffer = ByteBuffer.wrap(buffer, 0, 8).order(ByteOrder.LITTLE_ENDIAN);
        if (ndxStream.read(shortBuffer.array(), 0, shortBuffer.limit()) != shortBuffer.limit()) {
            throw new RuntimeException("size mismatched");
        }
        short size = shortBuffer.getShort();

        // should be zero
        shortBuffer.rewind();
        if (ndxStream.read(shortBuffer.array(), 0, shortBuffer.limit()) != shortBuffer.limit()) {
            throw new RuntimeException("size mismatched");
        }
        if (shortBuffer.getShort() != 0) {
            // todo
            throw new RuntimeException("not zero");
        }

        int pdtPos = 0;

        for (int i = 0; i < size; i++) {
            longBuffer.rewind();
            if (ndxStream.read(longBuffer.array(), 0, longBuffer.limit()) != longBuffer.limit()) {
                throw new RuntimeException("size mismatched");
            }
            long fileTime = longBuffer.getLong() / 10000 - DIFF_IN_MILLIS;
            Date date = new Date(fileTime);

            intBuffer.rewind();
            if (ndxStream.read(intBuffer.array(), 0, intBuffer.limit()) != intBuffer.limit()) {
                if (i != size - 1) {
                    throw new RuntimeException("size mismatched");
                }
            }
            int offset = (char) intBuffer.getShort();

            pdtPos += pdtStream.skip(offset - pdtPos);

            shortBuffer.rewind();
            pdtPos += pdtStream.read(shortBuffer.array(), 0, shortBuffer.limit());
            int length = (char) shortBuffer.getShort();

            if (buffer.length < length) {
                buffer = new byte[length + length / 2];
            }

            pdtPos += pdtStream.read(buffer, 0, length);

            list.add(new TeleProgramItem(new String(buffer, 0, length, "Cp1251"), date));
        }

        return list;
    }

    public void save2XMLTV(List<TeleProgramItem> list, OutputStream outputStream) throws IOException, XMLStreamException {
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter xml = xmlOutputFactory.createXMLStreamWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        try {
            xml.writeStartDocument("UTF-8", "1.0");
            xml.writeDTD("<!DOCTYPE tv SYSTEM \"http://www.teleguide.info/xmltv.dtd\">");
            xml.writeEmptyElement("tv");
            // todo: implement tv version
            xml.writeAttribute("generator-info-name", "mimas.tv/" + Mimas.getConfig().getVersion());
            xml.writeAttribute("generator-info-url", "http://mimas.hlops.ru/tv");
            xml.writeEndDocument();
        } finally {
            xml.close();
        }
    }
}

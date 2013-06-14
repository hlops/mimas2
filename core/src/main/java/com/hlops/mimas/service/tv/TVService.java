package com.hlops.mimas.service.tv;

import com.hlops.mimas.data.bean.tv.TeleProgramItem;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 09.05.13
 * Time: 18:57
 * To change this template use File | Settings | File Templates.
 */
public interface TVService {

    List<TeleProgramItem> load(InputStream ndxStream, InputStream pdtStream) throws IOException;

    void save2XMLTV(List<TeleProgramItem> list, OutputStream out) throws IOException, XMLStreamException;
}

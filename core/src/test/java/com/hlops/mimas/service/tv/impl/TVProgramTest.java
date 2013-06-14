package com.hlops.mimas.service.tv.impl;

import com.sun.xml.internal.stream.XMLInputFactoryImpl;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 11.05.13
 * Time: 18:36
 * To change this template use File | Settings | File Templates.
 */
public class TVProgramTest extends Assert {

    @Test
    public void testProgram() throws Exception {
        List<Channel> channelsFromXMLTV = getChannelsFromXMLTV("D:\\Distr\\Downloads\\xmltv.xml.gz");
        List<Channel> channelsFromM3U = getChannelsFromM3U("D:\\Distr\\Downloads\\playlist (6).m3u");

        Map<String, Channel> map = new HashMap<String, Channel>();
        for (Channel c : channelsFromM3U) {
            map.put(c.getName().trim().toLowerCase(), c);
        }

        for (Channel c : channelsFromXMLTV) {
            Channel c1 = map.get(c.getName().trim().toLowerCase());
            if (c1 != null) {
                c1.name = c.name;
                //channelsFromM3U.remove(c1);
            }
        }

        Map<String, String> channelManualSubstitution = new HashMap<String, String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("core/src/test/java/com/hlops/mimas/service/tv/impl/channels.txt"), "UTF-8"));
        try {
            String s;
            while ((s = reader.readLine()) != null) {
                String[] strings = s.split("\t");
                if (strings.length > 3 && strings[3] != null) {
                    channelManualSubstitution.put(strings[0], strings[3]);
                }
            }
        } finally {
            reader.close();
        }

        for (Channel c : channelsFromM3U) {
            if (channelManualSubstitution.containsKey(c.getId())) {
                c.name = channelManualSubstitution.get(c.getId());
            }
        }

        //Collections.sort(channelsFromM3U);
        for (Channel c : channelsFromM3U) {
            //c.timeShift = "0";
            System.out.println(c.toM3UString());
        }
    }

    class Channel implements Comparable<Channel> {
        private String id, name;
        private String group, url, timeShift;

        Channel(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Channel o) {
            return new Integer(id).compareTo(Integer.parseInt(o.id));
        }

        @Override
        public String toString() {
            return id + "\t" + name + "\t" + group;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String toM3UString() {
            StringBuilder sb = new StringBuilder();
            sb.append("#EXTINF:").append(timeShift).append(" tvg-name=").append(id).append(" ,").append(name);
            sb.append("\nhttp://192.168.1.1:81/udp/").append(url.split("@")[1]);
            return sb.toString();
        }
    }

    private List<Channel> getChannelsFromXMLTV(String path) throws IOException, XMLStreamException {
        List<Channel> result = new ArrayList<Channel>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(path)), "UTF-8"));
        try {
            XMLInputFactory xmlInputFactory = new XMLInputFactoryImpl();
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(reader);
            StartElement channel = null, displayName = null;
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (channel == null && xmlEvent.isStartElement() && xmlEvent.asStartElement().getName().getLocalPart().equals("channel")) {
                    channel = xmlEvent.asStartElement();
                } else if (channel != null && xmlEvent.isEndElement() && xmlEvent.asEndElement().getName().getLocalPart().equals("channel")) {
                    channel = null;
                } else if (channel != null && xmlEvent.isStartElement() && xmlEvent.asStartElement().getName().getLocalPart().equals("display-name")) {
                    displayName = xmlEvent.asStartElement();
                } else if (displayName != null && xmlEvent.isEndElement() && xmlEvent.asEndElement().getName().getLocalPart().equals("display-name")) {
                    displayName = null;
                } else if (displayName != null && xmlEvent.isCharacters()) {
                    result.add(new Channel(channel.getAttributeByName(new QName("id")).getValue(), xmlEvent.asCharacters().getData()));
                }
            }
        } finally {
            reader.close();
        }

        return result;
    }

    private static final Pattern PATTERN_EXTINF = Pattern.compile("#EXTINF:(-\\d+) tvg-name=(\\d+) (aspect-ratio=\\d+:\\d+ )?(crop=[0-9x+]+ )?(group-title=\"([0-9A-zА-я -.]+)\" )?,([0-9A-zА-я -.]+)");

    private List<Channel> getChannelsFromM3U(String path) throws IOException {
        List<Channel> result = new ArrayList<Channel>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "Cp1251"));
        try {
            String s, group = "";
            Channel channel = null;
            while ((s = br.readLine()) != null) {
                if (s.startsWith("#EXTINF:")) {
                    if (channel != null) {
                        result.add(channel);
                    }
                    Matcher m = PATTERN_EXTINF.matcher(s);
                    if (!m.matches()) {
                        throw new RuntimeException("not match: " + s);
                    }
                    channel = new Channel(m.group(2), m.group(7));
                    if (m.group(6) != null) {
                        group = m.group(6);
                    }
                    channel.group = group;
                    channel.timeShift = m.group(1);
                } else if (channel != null && s.startsWith("udp://@")) {
                    channel.url = s;
                }
            }
            if (channel != null) {
                result.add(channel);
            }
        } finally {
            br.close();
        }
        return result;
    }

}

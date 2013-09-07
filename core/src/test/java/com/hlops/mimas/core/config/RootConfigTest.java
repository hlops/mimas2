package com.hlops.mimas.core.config;

import com.hlops.mimas.core.data.bean.rootManager.RootManagerBean;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 07.09.13
 * Time: 12:33
 * To change this template use File | Settings | File Templates.
 */
public class RootConfigTest extends Assert {

    private RootConfig config;

    @Before
    public void setUp() throws Exception {
        final List<RootManagerBean> roots = new ArrayList<>();
        roots.add(new RootManagerBean() {
            {
                setId("id1");
                setPath("path1");
                setCaseSensitive(true);
                setSyntax(FileSystemSyntax.glob);
                getIncludes().add("aaa1");
                getIncludes().add("bbb1");
                getExcludes().add("ccc1");
            }
        });
        roots.add(new RootManagerBean() {
            {
                setId("id2");
                setPath("path2");
                setCaseSensitive(false);
                setSyntax(FileSystemSyntax.regexp);
                getIncludes().add("aaa2");
                getIncludes().add("bbb2");
                getExcludes().add("ccc2");
            }
        });
        config = new RootConfig() {
            {
                setRoots(roots);
            }
        };
    }

    @Test
    public void testGetRoot() throws Exception {
        assertEquals("path1", config.getRoot("id1").getPath());
        assertEquals("path2", config.getRoot("id2").getPath());
        assertNull(config.getRoot("no_such_id"));
    }

    @Test
    public void testXmlBean() throws Exception {
        JAXBContext jc = JAXBContext.newInstance(RootConfig.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // print out
        marshaller.marshal(config, System.out);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        marshaller.marshal(config, os);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        RootConfig newConfig = (RootConfig) unmarshaller.unmarshal(new ByteArrayInputStream(os.toByteArray()));

        assertNotNull(newConfig);
        assertNotNull(newConfig.getRoots());
        assertEquals(config.getRoots().size(), newConfig.getRoots().size());

        for (int i = 0; i < config.getRoots().size(); i++) {
            RootManagerBean bean1 = config.getRoots().get(i);
            RootManagerBean bean2 = newConfig.getRoots().get(i);

            assertEquals(bean1.getId(), bean2.getId());
            assertEquals(bean1.getPath(), bean2.getPath());
            assertEquals(bean1.getSyntax(), bean2.getSyntax());
            assertEquals(bean1.isCaseSensitive(), bean2.isCaseSensitive());
            assertEquals(bean1.getIncludes().size(), bean2.getIncludes().size());
            assertEquals(bean1.getExcludes().size(), bean2.getExcludes().size());
        }
    }
}

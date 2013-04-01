package com.hlops.mimas.config;

import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 27.03.13
 * Time: 22:45
 * To change this template use File | Settings | File Templates.
 */
public class MimasConfigTest extends Assert {

    @Test
    public void testPrintConfig() throws Exception {
        JAXBContext jc = JAXBContext.newInstance(MimasConfig.class);

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(MimasConfig.getInstance(), System.out);
    }

    @Test
    public void testConfig() throws Exception {
        assertNotNull(MimasConfig.getInstance().getVersion());
        assertNotNull(MimasConfig.getInstance().getDateFormat());
    }

    @Test
    public void testPrintSchema() throws Exception {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        JAXBContext jc = JAXBContext.newInstance(MimasConfig.class);
        jc.generateSchema(new SchemaOutputResolver() {
            @Override
            public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
                StreamResult result = new StreamResult(os);
                result.setSystemId(suggestedFileName);
                return result;
            }
        });
        System.out.println(os.toString());
    }

}

package com.hlops.mimas.test.core.config;

import com.hlops.mimas.core.config.Mimas;
import com.hlops.mimas.core.config.MimasConfig;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
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
        marshaller.marshal(new MimasConfig(), System.out);
    }

    @Test
    public void testConfig() throws Exception {
        assertNotNull(Mimas.getConfig().getVersion());
        assertNotNull(Mimas.getConfig().getDateFormat());
    }

    @Test
    public void testPrintSchema() throws Exception {
        JAXBContext jc = JAXBContext.newInstance(MimasConfig.class);
        jc.generateSchema(new SchemaOutputResolver() {
            @Override
            public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
                if ("schema1.xsd".equals(suggestedFileName)) {
                    suggestedFileName = "mimas.xsd";
                }
                StreamResult result = new StreamResult(new File("./core/src/main/resources/", suggestedFileName));
                result.setSystemId(suggestedFileName);
                return result;
            }
        });
    }

}

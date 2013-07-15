package com.hlops.mimas.test.logic.root.impl;

import com.hlops.mimas.logic.root.bean.RootBean;
import com.hlops.mimas.logic.root.bean.RootManagerBean;
import junit.framework.TestCase;
import org.apache.maven.shared.model.fileset.FileSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 7/15/13
 * Time: 8:00 PM
 */
public class RootManagerImplTest extends TestCase {

    public static void main(String[] args) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(RootManagerBean.class);

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        final RootManagerBean rootManager = new RootManagerBean();
        FileSet fileSet = new FileSet();
        fileSet.setIncludes(Arrays.asList(new String[]{"*.gif", "**/*.gif"}));
        rootManager.getRoots().add(new RootBean("id", "path", fileSet, "image/gif"));
        marshaller.marshal(rootManager, System.out);
    }
}

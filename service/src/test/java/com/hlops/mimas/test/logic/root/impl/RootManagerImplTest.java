package com.hlops.mimas.test.logic.root.impl;

import com.hlops.mimas.core.data.bean.rootManager.RootManagerBean;
import junit.framework.TestCase;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

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

        marshaller.marshal(rootManager, System.out);
    }
}

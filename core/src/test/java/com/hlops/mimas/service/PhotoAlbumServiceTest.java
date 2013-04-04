package com.hlops.mimas.service;

import com.google.inject.Inject;
import com.hlops.mimas.data.bean.photo.PhotoAlbum;
import com.hlops.mimas.data.key.photo.PhotoAlbumKey;
import com.hlops.mimas.module.ServiceModule;
import com.hlops.mimas.service.photo.PhotoService;
import com.hlops.mimas.test.GuiceJUnitRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 30.03.13
 * Time: 1:32
 * To change this template use File | Settings | File Templates.
 */
@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({ServiceModule.class})
public class PhotoAlbumServiceTest extends Assert {

    @Inject
    private PhotoService photoService;

    @Test
    public void testGetConfig() throws Exception {
        File path = new File("core/src/test/resources/foto");
        PhotoAlbum config = photoService.getAlbum(new PhotoAlbumKey(path));

        JAXBContext jc = JAXBContext.newInstance(PhotoAlbum.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(config, System.out);
    }
}

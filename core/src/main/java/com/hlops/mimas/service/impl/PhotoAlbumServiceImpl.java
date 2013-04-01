package com.hlops.mimas.service.impl;

import com.hlops.mimas.data.bean.photo.PhotoAlbum;
import com.hlops.mimas.config.MimasConfig;
import com.hlops.mimas.service.PhotoAlbumService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 30.03.13
 * Time: 1:23
 * To change this template use File | Settings | File Templates.
 */
public class PhotoAlbumServiceImpl implements PhotoAlbumService {

    public PhotoAlbum getAlbum(File path) throws JAXBException {
        File configFile = new File(path, MimasConfig.getInstance().getDefaultMimasFolder());
        PhotoAlbum album;
        if (configFile.exists()) {
            JAXBContext jc = JAXBContext.newInstance(MimasConfig.class);
            album = (PhotoAlbum) jc.createUnmarshaller().unmarshal(configFile);
        } else {
            album = new PhotoAlbum(path.getName());
        }
        if (!isActual(configFile, album)) {

        }

        return album;
    }

    @SuppressWarnings("RedundantIfStatement")
    private boolean isActual(File configFile, PhotoAlbum album) {
        if (!configFile.exists() || configFile.lastModified() >= configFile.getParentFile().lastModified()) {
            return false;
        }
        if (album.getVersion().compareTo(MimasConfig.getInstance().getPhotoConfig().getVersion()) == 1) {
            return false;
        }
        return true;
    }
}

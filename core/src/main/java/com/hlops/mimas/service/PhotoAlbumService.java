package com.hlops.mimas.service;

import com.hlops.mimas.data.bean.photo.PhotoAlbum;

import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 30.03.13
 * Time: 1:18
 * To change this template use File | Settings | File Templates.
 */
public interface PhotoAlbumService {

    PhotoAlbum getAlbum(File path) throws JAXBException;
}

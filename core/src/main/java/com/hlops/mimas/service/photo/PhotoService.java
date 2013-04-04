package com.hlops.mimas.service.photo;

import com.hlops.mimas.data.bean.photo.Photo;
import com.hlops.mimas.data.bean.photo.PhotoAlbum;
import com.hlops.mimas.data.key.photo.PhotoAlbumKey;
import com.hlops.mimas.data.key.photo.PhotoKey;

import javax.xml.bind.JAXBException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 30.03.13
 * Time: 1:18
 * To change this template use File | Settings | File Templates.
 */
public interface PhotoService {

    PhotoAlbum getAlbum(PhotoAlbumKey key) throws JAXBException, ExecutionException, InterruptedException;

    Photo getPhoto(PhotoKey key) throws JAXBException, ExecutionException, InterruptedException;

    Photo getPhoto(PhotoKey key, int timeout) throws JAXBException, ExecutionException, InterruptedException, TimeoutException;
}

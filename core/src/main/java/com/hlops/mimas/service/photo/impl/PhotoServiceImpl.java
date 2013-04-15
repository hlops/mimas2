package com.hlops.mimas.service.photo.impl;

import com.google.inject.Inject;
import com.hlops.mimas.data.bean.photo.Photo;
import com.hlops.mimas.data.bean.photo.PhotoAlbum;
import com.hlops.mimas.data.key.photo.PhotoAlbumKey;
import com.hlops.mimas.data.key.photo.PhotoKey;
import com.hlops.mimas.service.QueueService;
import com.hlops.mimas.service.photo.PhotoService;

import javax.xml.bind.JAXBException;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 30.03.13
 * Time: 1:23
 * To change this template use File | Settings | File Templates.
 */
public class PhotoServiceImpl implements PhotoService {

    @Inject
    private QueueService queue;

    // todo - handle exceptions
    public PhotoAlbum getAlbum(PhotoAlbumKey key) throws JAXBException, ExecutionException, InterruptedException {
        return queue.getFuture(new CreatePhotoAlbumTask(key, queue)).get();
    }

    public Photo getPhoto(PhotoKey key) throws JAXBException, ExecutionException, InterruptedException {
        return queue.getFuture(new CreatePhotoTask(key)).get();
    }

}

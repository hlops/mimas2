package com.hlops.mimas.service.photo.impl;

import com.hlops.mimas.data.bean.photo.ImageSize;
import com.hlops.mimas.data.bean.photo.Photo;
import com.hlops.mimas.data.key.photo.PhotoKey;
import com.hlops.mimas.sync.CallableTask;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 02.04.13
 * Time: 1:03
 * To change this template use File | Settings | File Templates.
 */
class CreatePhotoTask implements CallableTask<PhotoKey, Photo> {

    private final PhotoKey key;

    CreatePhotoTask(PhotoKey key) {
        this.key = key;
    }

    public PhotoKey getKey() {
        return key;
    }

    private Photo getPhoto(PhotoKey key) throws IOException {
        System.out.println(key.getFile());
        BufferedImage image = ImageIO.read(key.getFile());
        Photo foto = new Photo(key.getName(), null, new ImageSize(image.getWidth(), image.getHeight()));
        System.out.println(key.getFile()+" done");
        return foto;
    }

    public Photo call() throws Exception {
        return getPhoto(key);
    }
}

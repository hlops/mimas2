package com.hlops.mimas.service.photo.impl;

import com.hlops.mimas.data.TaskKey;
import com.hlops.mimas.data.bean.photo.ImageSize;
import com.hlops.mimas.data.bean.photo.Photo;
import com.hlops.mimas.data.key.photo.PhotoKey;
import com.hlops.mimas.sync.CallableTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
class CreatePhotoTask implements CallableTask<Photo> {

    private static Logger logger = LoggerFactory.getLogger(CreatePhotoTask.class);

    private final PhotoKey key;
    private final TaskKey taskKey;

    CreatePhotoTask(PhotoKey key) {
        this.key = key;
        this.taskKey = new TaskKey(this.getClass(), key);
    }

    public TaskKey getKey() {
        return taskKey;
    }

    private Photo getPhoto(PhotoKey key) throws IOException {
        logger.debug("CreatePhotoTask.getPhoto {}", key.getFile().getName());
        BufferedImage image = ImageIO.read(key.getFile());
        return new Photo(key.getName(), null, new ImageSize(image.getWidth(), image.getHeight()));
    }

    public Photo call() throws Exception {
        return getPhoto(key);
    }
}

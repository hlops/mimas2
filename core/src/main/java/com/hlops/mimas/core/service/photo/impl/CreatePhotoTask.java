package com.hlops.mimas.core.service.photo.impl;

import com.hlops.mimas.core.data.TaskKey;
import com.hlops.mimas.core.data.bean.photo.ImageSize;
import com.hlops.mimas.core.data.bean.photo.Photo;
import com.hlops.mimas.core.data.key.photo.PhotoKey;
import com.hlops.mimas.core.service.ServiceManager;
import com.hlops.mimas.core.service.rootManager.RootManagerException;
import com.hlops.mimas.core.sync.CallableTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
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
    private final ServiceManager serviceManager;

    CreatePhotoTask(PhotoKey key, ServiceManager serviceManager) {
        this.key = key;
        this.taskKey = new TaskKey(this.getClass(), key);
        this.serviceManager = serviceManager;
    }

    public TaskKey getKey() {
        return taskKey;
    }

    private Photo getPhoto(PhotoKey key) throws IOException, RootManagerException {
        logger.debug("CreatePhotoTask.getPhoto {}", key.getName());
        File f = new File(serviceManager.getRootManagerService().getFile(key.getAlbum()), key.getName());
        BufferedImage image = ImageIO.read(f);
        return new Photo(key.getName(), null, new ImageSize(image.getWidth(), image.getHeight()), f.length(), f.lastModified());
    }

    public Photo call() throws Exception {
        return getPhoto(key);
    }

    @Override
    public boolean isBlocking() {
        return false;
    }
}

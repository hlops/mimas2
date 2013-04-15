package com.hlops.mimas.service.photo.impl;

import com.hlops.mimas.config.MimasConfig;
import com.hlops.mimas.data.TaskKey;
import com.hlops.mimas.data.bean.photo.PhotoAlbum;
import com.hlops.mimas.data.key.photo.PhotoAlbumKey;
import com.hlops.mimas.service.QueueService;
import com.hlops.mimas.sync.CallableTask;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 01.04.13
 * Time: 21:22
 * To change this template use File | Settings | File Templates.
 */
class CreatePhotoAlbumTask implements CallableTask<PhotoAlbum> {

    private final PhotoAlbumKey albumKey;
    private final TaskKey taskKey;
    private final QueueService queue;

    CreatePhotoAlbumTask(PhotoAlbumKey albumKey, QueueService queue) {
        this.albumKey = albumKey;
        this.queue = queue;
        taskKey = new TaskKey(this.getClass(), albumKey);
    }

    @Override
    public TaskKey getKey() {
        return taskKey;
    }

    @Override
    public PhotoAlbum call() throws Exception {
        return getAlbum(albumKey);
    }

    private PhotoAlbum getAlbum(PhotoAlbumKey key) throws JAXBException {
        File configFile = new File(key.getFile(), MimasConfig.getInstance().getDefaultMimasFolder());
        PhotoAlbum album;
        if (configFile.exists()) {
            JAXBContext jc = JAXBContext.newInstance(PhotoAlbum.class);
            album = (PhotoAlbum) jc.createUnmarshaller().unmarshal(configFile);
        } else {
            album = new PhotoAlbum(key.getFile().getName());
        }
        if (!isActual(configFile, album)) {
            System.out.println("actualize: "+albumKey);
            load(album, key, configFile);
            save(album, configFile);
        }

        return album;
    }

    @SuppressWarnings("RedundantIfStatement")
    private boolean isActual(File configFile, PhotoAlbum album) {
        if (!configFile.exists() || configFile.lastModified() < configFile.getParentFile().lastModified()) {
            return false;
        }
        if (!album.getVersion().isCompatible(MimasConfig.getInstance().getPhotoConfig().getVersion())) {
            return false;
        }
        return true;
    }

    private void load(PhotoAlbum album, PhotoAlbumKey key, File configFile) {
        try {
            queue.waitFuture(new ReadPhotosTask(queue, key, album, configFile));
        } catch (ExecutionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void save(PhotoAlbum album, File configFile) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(PhotoAlbum.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(album, configFile);
    }

}

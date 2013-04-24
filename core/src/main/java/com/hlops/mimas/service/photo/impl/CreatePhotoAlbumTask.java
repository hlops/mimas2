package com.hlops.mimas.service.photo.impl;

import com.hlops.mimas.config.MimasConfig;
import com.hlops.mimas.data.TaskKey;
import com.hlops.mimas.data.bean.photo.Photo;
import com.hlops.mimas.data.bean.photo.PhotoAlbum;
import com.hlops.mimas.data.key.photo.PhotoAlbumKey;
import com.hlops.mimas.data.key.photo.PhotoKey;
import com.hlops.mimas.service.QueueService;
import com.hlops.mimas.sync.CallableTask;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 01.04.13
 * Time: 21:22
 * To change this template use File | Settings | File Templates.
 */
class CreatePhotoAlbumTask implements CallableTask<PhotoAlbum> {

    private static Logger logger = LoggerFactory.getLogger(CreatePhotoAlbumTask.class);

    private final PhotoAlbumKey albumKey;
    private final TaskKey taskKey;
    private final QueueService queue;

    CreatePhotoAlbumTask(PhotoAlbumKey albumKey, QueueService queue) {
        this.albumKey = albumKey;
        this.queue = queue;
        taskKey = new TaskKey(CreatePhotoAlbumTask.class, albumKey);
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
        File configPath = new File(key.getFile(), MimasConfig.getInstance().getDefaultMimasFolder());
        if (!configPath.exists()) {
            configPath.mkdirs();
            try {
                Files.setAttribute(configPath.toPath(), "dos:hidden", true);
            } catch (IOException e) {
                logger.error(configPath.getAbsolutePath(), e);
            }
        }
        File configFile = new File(configPath, MimasConfig.getInstance().getPhotoConfig().getConfigName());

        PhotoAlbum album;
        if (configFile.exists()) {
            JAXBContext jc = JAXBContext.newInstance(PhotoAlbum.class);
            album = (PhotoAlbum) jc.createUnmarshaller().unmarshal(configFile);
        } else {
            album = new PhotoAlbum(key.getFile().getName());
        }
        if (!isActual(configFile, album)) {
            load(album, key, configFile);
            save(album, configFile);
        }

        return album;
    }

    @SuppressWarnings("RedundantIfStatement")
    private boolean isActual(File configFile, PhotoAlbum album) {
        if (MimasConfig.getInstance().getPhotoConfig().getRecreateConfig()) {
            return false;
        }

        if (!configFile.exists() || configFile.lastModified() < configFile.getParentFile().lastModified()) {
            return false;
        }
        if (!album.getVersion().isCompatible(MimasConfig.getInstance().getPhotoConfig().getVersion())) {
            return false;
        }
        return true;
    }

    private void load(final PhotoAlbum album, PhotoAlbumKey key, File configFile) {
        File[] files = key.getFile().listFiles(new FileFilter() {
            public boolean accept(File f) {
                //noinspection SimplifiableIfStatement
                if (wildcardMatches(f, MimasConfig.getInstance().getPhotoConfig().getIncludedWildcard())) {
                    return !wildcardMatches(f, album.getExcludedWildcard());
                }
                return false;
            }
        });
        List<Future> subTasks = new ArrayList<Future>();
        if (files != null) {
            for (File f : files) {
                CreatePhotoTask createPhotoTask = new CreatePhotoTask(new PhotoKey(key, f.getName()));
                subTasks.add(queue.getFuture(createPhotoTask));
            }
        }

        queue.incrementPoolSize();
        try {
        for (Future f : subTasks) {
            try {
                album.getItems().add((Photo) f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (ExecutionException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        } finally {
            queue.decrementPoolSize();
        }
    }

    private static boolean wildcardMatches(File f, @Nullable String wildcards) {
        if (StringUtils.isNotBlank(wildcards)) {
            //noinspection ConstantConditions
            for (String wildcard : wildcards.split("[ ,;]")) {
                if (FilenameUtils.wildcardMatch(f.getName(), wildcard, IOCase.INSENSITIVE)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void save(PhotoAlbum album, File configFile) throws JAXBException {
        logger.debug("save album config: {}", configFile);
        JAXBContext jc = JAXBContext.newInstance(PhotoAlbum.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(album, configFile);
    }

}

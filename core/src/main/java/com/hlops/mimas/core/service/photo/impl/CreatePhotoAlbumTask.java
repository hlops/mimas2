package com.hlops.mimas.core.service.photo.impl;

import com.hlops.mimas.core.Mimas;
import com.hlops.mimas.core.data.TaskKey;
import com.hlops.mimas.core.data.bean.photo.Photo;
import com.hlops.mimas.core.data.bean.photo.PhotoAlbum;
import com.hlops.mimas.core.data.key.photo.PhotoAlbumKey;
import com.hlops.mimas.core.data.key.photo.PhotoKey;
import com.hlops.mimas.core.service.ServiceManager;
import com.hlops.mimas.core.service.rootManager.RootManagerException;
import com.hlops.mimas.core.sync.CallableTask;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private final ServiceManager serviceManager;

    CreatePhotoAlbumTask(@NotNull PhotoAlbumKey albumKey, @NotNull ServiceManager serviceManager) {
        this.albumKey = albumKey;
        this.serviceManager = serviceManager;
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

    private void createConfig(File configPath) {
        //noinspection ResultOfMethodCallIgnored
        configPath.mkdirs();
        try {
            Files.setAttribute(configPath.toPath(), "dos:hidden", true);
        } catch (IOException e) {
            logger.error(configPath.getAbsolutePath(), e);
        }
    }

    private PhotoAlbum getAlbum(PhotoAlbumKey key) throws JAXBException, RootManagerException {
        File file = serviceManager.getRootManagerService().getFile(key.getRoot(), key.getPath());
        File configPath = new File(file, Mimas.getConfig().getMimasFolderName());
        if (!configPath.exists()) {
            createConfig(configPath);
        }
        File configFile = new File(configPath, Mimas.getConfig().getPhotoConfig().getConfigFileName());

        PhotoAlbum album;
        if (configFile.exists()) {
            JAXBContext jc = JAXBContext.newInstance(PhotoAlbum.class);
            album = (PhotoAlbum) jc.createUnmarshaller().unmarshal(configFile);
        } else {
            album = new PhotoAlbum(key);
        }
        if (!isActual(configFile, album)) {
            album = load(album, key);
            save(album, configFile);
        }

        return album;
    }

    @SuppressWarnings("RedundantIfStatement")
    private boolean isActual(File configFile, PhotoAlbum album) {
        if (Mimas.getConfig().getPhotoConfig().isRecreateConfig()) {
            return false;
        }

        if (!configFile.exists() || configFile.lastModified() < configFile.getParentFile().getParentFile().lastModified()) {
            return false;
        }
        if (!album.getVersion().isCompatible(Mimas.getConfig().getVersion())) {
            return false;
        }
        return true;
    }

    private PhotoAlbum load(final PhotoAlbum oldAlbum, PhotoAlbumKey key) throws RootManagerException {
        final PhotoAlbum newAlbum = new PhotoAlbum(key);
        Map<String, Photo> oldPhotos = new HashMap<String, Photo>();
        for (Photo photo : oldAlbum.getItems()) {
            oldPhotos.put(photo.getName(), photo);
        }

        File file = serviceManager.getRootManagerService().getFile(key.getRoot(), key.getPath());
        File[] files = file.listFiles(new FileFilter() {
            public boolean accept(File f) {
                //noinspection SimplifiableIfStatement
                if (wildcardMatches(f, Mimas.getConfig().getPhotoConfig().getIncludedWildcard())) {
                    return !wildcardMatches(f, oldAlbum.getExcludedWildcard());
                }
                return false;
            }
        });
        List<Future<Photo>> subTasks = new ArrayList<Future<Photo>>();
        if (files != null) {
            for (File f : files) {
                Photo photo = oldPhotos.get(f.getName());
                if (photo != null && f.lastModified() == photo.getTimestamp() && f.length() == photo.getLength()) {
                    // not modified
                    newAlbum.getItems().add(photo);
                } else {
                    CreatePhotoTask createPhotoTask = new CreatePhotoTask(new PhotoKey(key, f.getName()), serviceManager);
                    subTasks.add(serviceManager.getQueueService().getFuture(createPhotoTask));
                }
            }
        }

        for (Future<Photo> f : subTasks) {
            try {
                newAlbum.getItems().add(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (ExecutionException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        return newAlbum;
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

    @Override
    public boolean isBlocking() {
        return true;
    }
}

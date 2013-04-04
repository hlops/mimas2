package com.hlops.mimas.service.photo.impl;

import com.google.inject.Guice;
import com.hlops.mimas.config.MimasConfig;
import com.hlops.mimas.data.bean.photo.PhotoAlbum;
import com.hlops.mimas.data.key.photo.PhotoAlbumKey;
import com.hlops.mimas.data.key.photo.PhotoKey;
import com.hlops.mimas.module.ServiceModule;
import com.hlops.mimas.service.photo.PhotoService;
import com.hlops.mimas.sync.CallableTask;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 01.04.13
 * Time: 21:22
 * To change this template use File | Settings | File Templates.
 */
class CreatePhotoAlbumTask implements CallableTask<PhotoAlbumKey, PhotoAlbum> {

    private final PhotoAlbumKey key;
    private PhotoService photoService;

    CreatePhotoAlbumTask(PhotoAlbumKey key, PhotoService photoService) {
        this.key = key;
        this.photoService = photoService;
    }

    public PhotoAlbumKey getKey() {
        return key;
    }

    public PhotoAlbum call() throws Exception {
        return getAlbum(key);
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

    private void load(final PhotoAlbum album, PhotoAlbumKey key, File configFile) {
        File[] files = configFile.getParentFile().listFiles(new FileFilter() {
            public boolean accept(File f) {
                //noinspection SimplifiableIfStatement
                if (wildcardMatches(f, MimasConfig.getInstance().getPhotoConfig().getIncludedWildcard())) {
                    return !wildcardMatches(f, album.getExcludedWildcard());
                }
                return false;
            }
        });

        if (files != null) {
            for (File f : files) {
                try {
                    photoService.getPhoto(new PhotoKey(key, f.getName()), 1);
                } catch (JAXBException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (ExecutionException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (TimeoutException e) {
                    //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                //addImage(album, f);
                //System.out.println(f);
            }
            for (File f : files) {
                try {
                    album.getItems().add(photoService.getPhoto(new PhotoKey(key, f.getName())));
                } catch (JAXBException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (ExecutionException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                //addImage(album, f);
                //System.out.println(f);
            }
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
        JAXBContext jc = JAXBContext.newInstance(PhotoAlbum.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(album, configFile);
    }

}

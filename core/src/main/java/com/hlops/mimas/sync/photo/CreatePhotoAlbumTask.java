package com.hlops.mimas.sync.photo;

import com.hlops.mimas.config.MimasConfig;
import com.hlops.mimas.data.bean.photo.PhotoAlbum;
import com.hlops.mimas.data.key.photo.PhotoAlbumKey;
import com.hlops.mimas.data.key.photo.PhotoKey;
import com.hlops.mimas.sync.EntityKeyFuture;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 01.04.13
 * Time: 21:22
 * To change this template use File | Settings | File Templates.
 */
public class CreatePhotoAlbumTask extends FutureTask<PhotoAlbum> implements EntityKeyFuture<PhotoAlbum, PhotoAlbumKey> {

    private final PhotoAlbumKey key;

    public CreatePhotoAlbumTask(final PhotoAlbumKey key) {
        super(new Callable<PhotoAlbum>() {
            public PhotoAlbum call() throws Exception {
                return getAlbum(key);
            }
        });
        this.key = key;
    }

    public PhotoAlbumKey getKey() {
        return key;
    }

    public static PhotoAlbum getAlbum(PhotoAlbumKey key) throws JAXBException {
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
    public static boolean isActual(File configFile, PhotoAlbum album) {
        if (!configFile.exists() || configFile.lastModified() < configFile.getParentFile().lastModified()) {
            return false;
        }
        if (!album.getVersion().isCompatible(MimasConfig.getInstance().getPhotoConfig().getVersion())) {
            return false;
        }
        return true;
    }

    public static void load(final PhotoAlbum album, PhotoAlbumKey key, File configFile) {
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
                //addImage(album, f);
                //System.out.println(f);
                try {
                    album.getItems().add(CreatePhotoTask.getPhoto(new PhotoKey(key, f.getName())));
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
    }

    public static boolean wildcardMatches(File f, @Nullable String wildcards) {
        if (StringUtils.isNotBlank(wildcards)) {
            for (String wildcard : wildcards.split("[ ,;]")) {
                if (FilenameUtils.wildcardMatch(f.getName(), wildcard, IOCase.INSENSITIVE)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void save(PhotoAlbum album, File configFile) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(PhotoAlbum.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(album, configFile);
    }

}

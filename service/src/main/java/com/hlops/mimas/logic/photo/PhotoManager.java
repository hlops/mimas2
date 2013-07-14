package com.hlops.mimas.logic.photo;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.hlops.mimas.core.data.bean.photo.Photo;
import com.hlops.mimas.core.data.bean.photo.PhotoAlbum;
import com.hlops.mimas.core.service.photo.PhotoService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 14.07.13
 * Time: 22:56
 * To change this template use File | Settings | File Templates.
 */
public class PhotoManager {

    private static final PhotoManager instance = new PhotoManager();

    public static PhotoManager getInstance() {
        return instance;
    }

    public List<Photo> find(@NotNull PhotoAlbum album, @Nullable String query) throws JAXBException, ExecutionException, InterruptedException {
        List<Photo> list = new ArrayList<Photo>();

        for (Photo photo : album.getItems()) {
            list.add(photo);
        }

        return list;
    }
}

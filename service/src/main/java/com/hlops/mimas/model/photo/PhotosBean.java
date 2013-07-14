package com.hlops.mimas.model.photo;

import com.hlops.mimas.core.data.bean.photo.Photo;
import com.hlops.mimas.core.data.bean.photo.PhotoAlbum;
import com.hlops.mimas.model.ModelBean;
import com.hlops.mimas.model.leftMenu.MenuBean;
import com.hlops.mimas.model.leftMenu.impl.LeftMenuOrder;
import com.hlops.mimas.model.leftMenu.impl.LeftMenuView;
import com.hlops.mimas.utils.CookieProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.annotation.XmlList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 24.06.13
 * Time: 1:27
 * To change this template use File | Settings | File Templates.
 */
public class PhotosBean extends ModelBean {

    public static final String ID = LEFT_MENU_ID_PREFIX + "Ph";

    private String name, description;

    @XmlList
    private Map<String, PhotoAlbumBean> albums = new HashMap<String, PhotoAlbumBean>();

    @XmlList
    private List<PhotoItemBean> photos = new ArrayList<PhotoItemBean>();

    public PhotosBean(CookieProvider cookieProvider) {
        super(cookieProvider);
    }

    @Override
    protected void createLeftMenu(CookieProvider cookieProvider, List<MenuBean> leftMenu) {
        leftMenu.add(new LeftMenuOrder(ID, cookieProvider));
        leftMenu.add(new LeftMenuView(ID, cookieProvider));
    }

    @Nullable
    public MenuBean getLeftMenuItem(@NotNull String id) {
        for (MenuBean bean : getLeftMenu()) {
            if (id.equals(bean.getId())) {
                return bean;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<PhotoItemBean> getPhotos() {
        return photos;
    }

    public Map<String, PhotoAlbumBean> getAlbums() {
        return albums;
    }

    public void add(PhotoAlbum album, Photo photo) {
        String albumId = addAlbum(album);
        getPhotos().add(new PhotoItemBean(albumId, photo));
    }

    private String addAlbum(PhotoAlbum album) {
        String albumName = album.getName();
        if (!albums.containsKey(albumName)) {
            albums.put(albumName, new PhotoAlbumBean(album));
        }
        return albumName;
    }

    public void addAll(PhotoAlbum album, List<Photo> photos) {
        for (Photo photo: photos) {
            add(album, photo);
        }
    }
}

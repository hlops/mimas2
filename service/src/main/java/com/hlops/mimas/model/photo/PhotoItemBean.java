package com.hlops.mimas.model.photo;

import com.hlops.mimas.core.data.bean.photo.Photo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 24.06.13
 * Time: 1:27
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class PhotoItemBean {

    private final String albumId;
    private final String name;
    private final String description;
    private final int width;
    private final int height;

    public PhotoItemBean(String albumId, Photo photo) {
        this.albumId = albumId;
        this.name = photo.getName();
        this.description = photo.getDescription();
        this.width = photo.getImageSize().getWidth();
        this.height = photo.getImageSize().getHeight();
    }

    public String getAlbumId() {
        return albumId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

package com.hlops.mimas.model.photo;

import com.hlops.mimas.core.data.bean.photo.PhotoAlbum;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 24.06.13
 * Time: 1:27
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class PhotoAlbumBean {

    private String name;
    private String description;
    private String ref;

    public PhotoAlbumBean(PhotoAlbum album) {
        this.name = album.getName();
        this.description = album.getDescription();
        //this.ref = album.;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRef() {
        return ref;
    }
}

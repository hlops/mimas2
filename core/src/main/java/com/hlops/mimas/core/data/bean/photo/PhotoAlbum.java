package com.hlops.mimas.core.data.bean.photo;

import com.hlops.mimas.core.config.MimasConfig;
import com.hlops.mimas.core.data.bean.Version;
import com.hlops.mimas.core.data.key.photo.PhotoAlbumKey;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 29.03.13
 * Time: 23:55
 * To change this template use File | Settings | File Templates.
 */

@XmlRootElement(name = "photoAlbum")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhotoAlbum {

    @XmlElement(required = true)
    private Version version;

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = false)
    private String description;

    @XmlElement(required = true)
    private List<Photo> items = new ArrayList<Photo>();

    @XmlElement(required = false)
    private String excludedWildcard;

    private PhotoAlbum() {
    }

    public PhotoAlbum(PhotoAlbumKey key) {
        this(key.getFile().getName());
    }

    public PhotoAlbum(String name) {
        setName(name);
        setVersion(MimasConfig.getInstance().getVersion());
    }

    @NotNull
    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    public List<Photo> getItems() {
        return items;
    }

    public String getExcludedWildcard() {
        return excludedWildcard;
    }

}

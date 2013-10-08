package com.hlops.mimas.core.data.key.photo;

import com.hlops.mimas.core.data.EntityKey;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 01.04.13
 * Time: 17:34
 * To change this template use File | Settings | File Templates.
 */
public class PhotoKey implements EntityKey {

    private final PhotoAlbumKey album;
    private final String name;

    public PhotoKey(@NotNull PhotoAlbumKey album, @NotNull String name) {
        this.album = album;
        this.name = name;
    }

    @NotNull
    public PhotoAlbumKey getAlbum() {
        return album;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotoKey)) return false;

        PhotoKey that = (PhotoKey) o;

        if (!album.equals(that.album)) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = album.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PhotoKey: " + album + "/" + name;
    }

}

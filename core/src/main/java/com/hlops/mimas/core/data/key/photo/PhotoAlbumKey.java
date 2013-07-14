package com.hlops.mimas.core.data.key.photo;

import com.hlops.mimas.core.data.EntityKey;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 01.04.13
 * Time: 17:32
 * To change this template use File | Settings | File Templates.
 */
public class PhotoAlbumKey implements EntityKey {

    private final File file;

    public PhotoAlbumKey(@NotNull File f) {
        this(f.getAbsolutePath());
    }

    public PhotoAlbumKey(@NotNull String path) {
        this.file = new File(path);
    }

    @NotNull
    public File getFile() {
        return file;
    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotoAlbumKey)) return false;

        PhotoAlbumKey that = (PhotoAlbumKey) o;

        if (!file.equals(that.file)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return file.hashCode();
    }

    @Override
    public String toString() {
        return file.getName();
    }
}

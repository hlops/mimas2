package com.hlops.mimas.core.data.key.photo;

import com.hlops.mimas.core.data.FileKey;
import org.jetbrains.annotations.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 01.04.13
 * Time: 17:32
 * To change this template use File | Settings | File Templates.
 */
public class PhotoAlbumKey extends FileKey {

    public PhotoAlbumKey(@NotNull String root, @NotNull String path) {
        super(root, path);
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PhotoAlbumKey)) return false;
        return super.equals(o);
    }
}

package com.hlops.mimas.core.data;

import org.jetbrains.annotations.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 07.10.13
 * Time: 20:56
 * To change this template use File | Settings | File Templates.
 */
public class FileKey implements EntityKey {

    private final String root;
    private final String path;

    public FileKey(@NotNull String root, @NotNull String path) {
        this.root = root;
        this.path = path;
    }

    @NotNull
    public String getRoot() {
        return root;
    }

    @NotNull
    public String getPath() {
        return path;
    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileKey)) return false;

        FileKey fileKey = (FileKey) o;

        if (!path.equals(fileKey.path)) return false;
        if (!root.equals(fileKey.root)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = root.hashCode();
        result = 31 * result + path.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return root + ":" + path;
    }

}

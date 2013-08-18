package com.hlops.mimas.core.data.bean.photo;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 30.03.13
 * Time: 0:32
 * To change this template use File | Settings | File Templates.
 */
public class Photo {

    private String name;
    private String description;
    private ImageSize imageSize;
    private long length;
    private long timestamp;

    public Photo() {
    }

    public Photo(@NotNull String name, @Nullable String description, @NotNull ImageSize imageSize, long length, long timestamp) {
        this.imageSize = imageSize;
        this.description = description;
        this.name = name;
        this.length = length;
        this.timestamp = timestamp;
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

    public ImageSize getImageSize() {
        return imageSize;
    }

    public void setImageSize(ImageSize imageSize) {
        this.imageSize = imageSize;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
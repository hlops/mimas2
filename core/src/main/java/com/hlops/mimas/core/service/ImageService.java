package com.hlops.mimas.core.service;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 29.03.13
 * Time: 22:07
 * To change this template use File | Settings | File Templates.
 */
public interface ImageService {

    Size getScale(@NotNull Size from, @NotNull Size to);

    Size getImageSize(@NotNull File source) throws IOException;

    void resizeTo(@NotNull File source, @NotNull File dest, @NotNull Size size) throws IOException;
}

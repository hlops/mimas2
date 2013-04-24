package com.hlops.mimas.service.impl;

import com.hlops.mimas.service.ImageService;
import com.hlops.mimas.service.QueueService;
import com.hlops.mimas.service.Size;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 29.03.13
 * Time: 22:08
 * To change this template use File | Settings | File Templates.
 */
public class ImageServiceImpl implements ImageService {

    private static Logger logger = LoggerFactory.getLogger(ImageService.class);

    @NotNull
    public Size getScale(@NotNull Size from, @NotNull Size to) {
        int fromWidth = from.getWidth(), fromHeight = from.getHeight();
        int toWidth = to.getWidth(), toHeight = to.getHeight();

        if (toHeight == 0 || (toWidth != 0 && fromWidth / fromHeight > toWidth / toHeight)) {
            toHeight = toWidth * fromHeight / fromWidth;
        } else {
            toWidth = toHeight * fromWidth / fromHeight;
        }

        return Size.positive(toWidth, toHeight);
    }

    public Size getImageSize(@NotNull File source) throws IOException {
        BufferedImage img = ImageIO.read(source);
        return Size.positive(img.getWidth(), img.getHeight());
    }

    public void resizeTo(@NotNull File source, @NotNull File dest, @NotNull Size size) throws IOException {
        if (!source.exists()) {
            throw new IllegalArgumentException("Source file does not exist: " + source.getAbsolutePath());
        }

        BufferedImage originalImage = ImageIO.read(source);
        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
        BufferedImage resizedImage = resizeImage(originalImage, type, size);
        ImageIO.write(resizedImage, "jpg", dest);
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int type, Size size) {
        Map<RenderingHints.Key, Object> renderingHints = new HashMap<RenderingHints.Key, Object>();

        renderingHints.put(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        renderingHints.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        renderingHints.put(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);

        return resizeImage(originalImage, type, renderingHints, size);
    }

    public BufferedImage resizeImage(BufferedImage originalImage, int type, Map<RenderingHints.Key, Object> renderingHints, Size size) {

        Size scaled = getScale(Size.positive(originalImage.getWidth(), originalImage.getHeight()), size);
        int tw = originalImage.getWidth();
        int th = originalImage.getHeight();

        BufferedImage resultImage = originalImage;
        do {
            double f = .5;
            tw -= tw * f;
            th -= th * f;

            if (tw < scaled.getWidth() || th < scaled.getHeight()) {
                tw = scaled.getWidth();
                th = scaled.getHeight();
            }

            BufferedImage resizedImage = new BufferedImage(tw, th, type);
            Graphics2D g = resizedImage.createGraphics();
            for (Map.Entry<RenderingHints.Key, Object> entry : renderingHints.entrySet()) {
                g.setRenderingHint(entry.getKey(), entry.getValue());
            }
            g.drawImage(resultImage, 0, 0, tw, th, null);
            g.dispose();

            resultImage = resizedImage;
        } while (tw != scaled.getWidth() || th != scaled.getHeight());


        return resultImage;
    }

}

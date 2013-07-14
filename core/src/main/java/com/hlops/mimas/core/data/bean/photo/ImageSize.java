package com.hlops.mimas.core.data.bean.photo;

import javax.xml.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 02.04.13
 * Time: 1:27
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "imageSize")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ImageSize {

    private int width;
    private int height;
    private static final String DELIMETER = "x";

    public ImageSize() {
    }

    public ImageSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @XmlTransient
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @XmlTransient
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @XmlValue()
    public String getSize() {
        return width + DELIMETER + height;
    }

    public void setSize(String size) {
        String[] arr = size.split(DELIMETER);
        width = Integer.parseInt(arr[0]);
        height = Integer.parseInt(arr[1]);
    }


}

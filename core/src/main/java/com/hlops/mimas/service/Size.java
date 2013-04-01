package com.hlops.mimas.service;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 29.03.13
 * Time: 22:41
 * To change this template use File | Settings | File Templates.
 */
public class Size {

    private final int width;
    private final int height;

    private Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Size)) return false;

        Size size = (Size) o;

        if (height != size.height) return false;
        if (width != size.width) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        return result;
    }

    public static Size positive(int width, int height) {
        if (width == 0) {
            throw new IllegalArgumentException("Width is 0");
        }
        if (height == 0) {
            throw new IllegalArgumentException("Height is 0");
        }
        return notNegative(width, height);
    }

    public static Size positiveOrZero(int width, int height) {
        if (width == 0 && height == 0) {
            throw new IllegalArgumentException("Height and width are both 0");
        }
        return notNegative(width, height);
    }

    public static Size notNegative(int width, int height) {
        if (width < 0) {
            throw new IllegalArgumentException("Width is negative: " + width);
        }
        if (height < 0) {
            throw new IllegalArgumentException("Height is negative: " + height);
        }
        return new Size(width, height);
    }

    @Override
    public String toString() {
        return "Size(" + width + "," + height + ')';
    }
}

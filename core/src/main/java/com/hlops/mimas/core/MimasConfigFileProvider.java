package com.hlops.mimas.core;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 03.10.13
 * Time: 23:02
 * To change this template use File | Settings | File Templates.
 */
public abstract class MimasConfigFileProvider implements Comparable<MimasConfigFileProvider> {

    private final int order;

    protected MimasConfigFileProvider(int order) {
        this.order = order;
    }

    public abstract URL getResource() throws MalformedURLException;

    @Override
    public int compareTo(MimasConfigFileProvider o) {
        // desc order
        return Integer.compare(o.order, this.order);
    }
}

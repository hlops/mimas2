package com.hlops.mimas.core.service.rootManager;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 07.09.13
 * Time: 17:58
 * To change this template use File | Settings | File Templates.
 */
public class Path {

    private final String id;
    private final String name;

    public Path(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

package com.hlops.mimas.core.service.rootManager;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 07.09.13
 * Time: 18:04
 * To change this template use File | Settings | File Templates.
 */
public class RootNotFoundException extends RootManagerException {

    public RootNotFoundException(String id) {
        super("Root not found: " + id);
    }
}

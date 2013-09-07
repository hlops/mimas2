package com.hlops.mimas.core.service.rootManager;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 07.09.13
 * Time: 17:52
 * To change this template use File | Settings | File Templates.
 */
public class RootIdParseException extends RootException {

    public RootIdParseException(String path) {
        super("No root was found in " + path);
    }
}

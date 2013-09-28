package com.hlops.mimas.core.service.rootManager;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 08.09.13
 * Time: 0:32
 * To change this template use File | Settings | File Templates.
 */
public class RootExcludedException extends RootManagerException {

    public RootExcludedException(File f, String mask) {
        super("File \"" + f + "\" is explicitly excluded by mask \"" + mask + "\"");
    }

    public RootExcludedException(File f) {
        super("File \"" + f + "\" is not included in the list of permitted files");
    }
}

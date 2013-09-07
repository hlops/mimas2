package com.hlops.mimas.core.service.rootManager;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 22.08.13
 * Time: 23:49
 * To change this template use File | Settings | File Templates.
 */
public interface RootManagerService {

    public File getFile(String path) throws RootException;

    public File getFile(String id, String path) throws RootException;
}

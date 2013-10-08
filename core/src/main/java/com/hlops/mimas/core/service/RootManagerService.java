package com.hlops.mimas.core.service;

import com.hlops.mimas.core.data.FileKey;
import com.hlops.mimas.core.service.rootManager.RootManagerException;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 22.08.13
 * Time: 23:49
 * To change this template use File | Settings | File Templates.
 */
public interface RootManagerService {

    public File getFile(String path) throws RootManagerException;

    public File getFile(String id, String path) throws RootManagerException;

    public File getFile(FileKey fileKey) throws RootManagerException;
}

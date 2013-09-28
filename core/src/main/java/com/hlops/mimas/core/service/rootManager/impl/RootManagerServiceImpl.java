package com.hlops.mimas.core.service.rootManager.impl;

import com.hlops.mimas.core.Mimas;
import com.hlops.mimas.core.data.bean.rootManager.RootManagerBean;
import com.hlops.mimas.core.service.rootManager.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 22.08.13
 * Time: 23:49
 * To change this template use File | Settings | File Templates.
 */
public class RootManagerServiceImpl implements RootManagerService {

    public static final String PATH_SEPARATOR = "/";

    protected Path parsePath(String s) throws RootIdParseException {
        StringTokenizer st = new StringTokenizer(s, PATH_SEPARATOR, false);
        if (!st.hasMoreTokens()) {
            throw new RootIdParseException(s);
        }
        String id = st.nextToken();
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            sb.append(st.nextToken());
            if (st.hasMoreTokens()) {
                sb.append(PATH_SEPARATOR);
            }
        }

        return new Path(id, sb.toString());
    }

    @Override
    public File getFile(String s) throws RootManagerException {
        Path path = parsePath(s);
        return getFile(path.getId(), path.getName());
    }

    @Override
    public File getFile(String id, String path) throws RootManagerException {
        RootManagerBean root = Mimas.getConfig().getRootConfig().getRoot(id);
        if (root == null) {
            throw new RootNotFoundException(id);
        }

        File f = new File(root.getFile(), path);
        try {
            String canonicalPath = f.getCanonicalPath();
            if (!canonicalPath.startsWith(root.getFile().getCanonicalPath())) {
                throw new RootManagerException("File is not part of root \"" + root.getFile().getCanonicalPath() + "\" (" + canonicalPath + ")");
            }
        } catch (IOException e) {
            throw new RootManagerException(e);
        }

        for (String mask : root.getExcludes()) {
            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(root.getSyntax() + ":" + mask);
            if (pathMatcher.matches(f.toPath())) {
                throw new RootExcludedException(f, mask);
            }
        }

        boolean isIncluded = false;
        for (String mask : root.getIncludes()) {
            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(root.getSyntax() + ":" + mask);
            if (pathMatcher.matches(f.toPath().relativize(root.getFile().toPath()))) {
                isIncluded = true;
                break;
            }
        }
        if (!isIncluded) {
            throw new RootExcludedException(f);
        }

        return f;
    }
}

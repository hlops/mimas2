package com.hlops.mimas.test.logic.root.impl;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 7/16/13
 * Time: 6:28 PM
 */
public class Test {
    public static void main(String[] args) throws Exception {
        final FileSystem fileSystem = FileSystems.getDefault();
        final Path path = fileSystem.getPath("/home/METROSOFT/a.karnachuk/tom/Project/mimas/mimas2/core/pom.xml");
        String root = fileSystem.getPath("core/**").toAbsolutePath().toString();
        System.out.println(root);
        System.out.println(path);
        System.out.println(fileSystem.getPathMatcher("glob:" + root).matches(path));
    }
}

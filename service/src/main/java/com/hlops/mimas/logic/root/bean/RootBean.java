package com.hlops.mimas.logic.root.bean;

import org.apache.maven.shared.model.fileset.FileSet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 7/15/13
 * Time: 7:25 PM
 */
@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootBean {

    private String id;

    private String path;

    private FileSet fileSet;

    private String mimeType;

    public RootBean() {
    }

    public RootBean(String id, String path, FileSet fileSet, String mimeType) {
        this.id = id;
        this.path = path;
        this.fileSet = fileSet;
        this.mimeType = mimeType;
    }

    public String getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public FileSet getFileSet() {
        return fileSet;
    }

    public String getMimeType() {
        return mimeType;
    }
}

package com.hlops.mimas.core.data.bean.rootManager;

import javax.xml.bind.annotation.*;
import java.beans.Transient;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 7/15/13
 * Time: 7:25 PM
 */
@SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id", "path", "caseSensitive", "syntax", "includes", "excludes"})
public class RootManagerBean {

    public enum FileSystemSyntax {glob, regexp}

    private String id;

    private File path;

    private boolean caseSensitive = false;

    private FileSystemSyntax syntax = FileSystemSyntax.glob;

    private List<String> includes = new ArrayList<String>();

    private List<String> excludes = new ArrayList<String>();

    public RootManagerBean() {
    }

    protected RootManagerBean(String id, String path) {
        setId(id);
        setPath(path);
    }

    @XmlID
    @XmlAttribute(required = true)
    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    @XmlAttribute(required = true)
    public String getPath() {
        return path.getName();
    }

    protected void setPath(String path) {
        this.path = new File(path);
    }

    @XmlAttribute(required = false)
    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    protected void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public FileSystemSyntax getSyntax() {
        return syntax;
    }

    @XmlAttribute(required = false)
    public String isSyntax() {
        return syntax.name();
    }

    @Transient
    protected void setSyntax(FileSystemSyntax syntax) {
        this.syntax = syntax;
    }

    protected void setSyntax(String syntax) {
        this.syntax = FileSystemSyntax.valueOf(syntax);
    }

    @XmlElement(name = "include")
    public List<String> getIncludes() {
        return includes;
    }

    protected void setIncludes(List<String> includes) {
        this.includes = Collections.unmodifiableList(includes);
    }

    @XmlElement(name = "exclude")
    public List<String> getExcludes() {
        return excludes;
    }

    protected void setExcludes(List<String> excludes) {
        this.excludes = Collections.unmodifiableList(excludes);
    }

    @Transient
    public File getFile() {
        return path;
    }

}

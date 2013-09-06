package com.hlops.mimas.core.data.bean.rootManager;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
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
public class RootManagerBean {

    public enum FileSystemSyntax {glob, regexp}

    private String id;

    private String path;

    private boolean caseSensitive = false;

    private FileSystemSyntax syntax = FileSystemSyntax.glob;

    private List<String> includes = new ArrayList<String>();

    private List<String> excludes = new ArrayList<String>();

    public RootManagerBean() {
    }

    @XmlID
    @XmlAttribute(required = true)
    public String getId() {
        return id;
    }

    @XmlAttribute(required = true)
    public String getPath() {
        return path;
    }

    @XmlAttribute(required = false)
    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    @XmlAttribute(required = false)
    public String isSyntax() {
        return syntax.name();
    }

    protected void setSyntax(String syntax) {
        this.syntax = FileSystemSyntax.valueOf(syntax);
    }

    public List<String> getIncludes() {
        return includes;
    }

    public List<String> getExcludes() {
        return excludes;
    }

    protected void setIncludes(List<String> includes) {
        this.includes = Collections.unmodifiableList(includes);
    }

    protected void setExcludes(List<String> excludes) {
        this.excludes = Collections.unmodifiableList(excludes);
    }
}

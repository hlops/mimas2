package com.hlops.mimas.core.data.bean.rootManager;

import com.hlops.mimas.core.config.VersionConfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 7/15/13
 * Time: 7:25 PM
 */
@SuppressWarnings({"FieldCanBeLocal"})
@XmlRootElement(name = "rootManager")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootManagerBean {

    public enum FileSystemSyntax {glob, regexp}

    @XmlAttribute(required = true)
    private String id;

    @XmlAttribute(required = true)
    private String path;

    @XmlAttribute(required = false)
    private boolean caseSensitive = false;

    @XmlAttribute(required = false)
    private FileSystemSyntax syntax = FileSystemSyntax.glob;

    private List<String> includes = new ArrayList<String>();

    private List<String> excludes = new ArrayList<String>();

    public RootManagerBean() {
    }

    public String getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

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

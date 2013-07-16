package com.hlops.mimas.core.data.bean.rootManager;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 7/15/13
 * Time: 7:25 PM
 */
@XmlRootElement(name = "path")
@XmlAccessorType(XmlAccessType.FIELD)
public class PathBean {

    enum FileSystemSyntax {glob, regexp}

    private String root;

    private FileSystemSyntax syntax = FileSystemSyntax.glob;

    private List<String> includes = new ArrayList<String>();

    private List<String> excludes = new ArrayList<String>();

    @XmlElement(name = "path")
    private List<PathBean> roots = new ArrayList<PathBean>();

    public PathBean() {
    }

    public PathBean(String root) {
        this(root, FileSystemSyntax.glob);
    }

    public PathBean(String root, FileSystemSyntax syntax) {
        this.root = root;
        this.syntax = syntax;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getSyntax() {
        return syntax.name();
    }

    public void setSyntax(String syntax) {
        this.syntax = FileSystemSyntax.valueOf(syntax);
    }

    public List<String> getIncludes() {
        return includes;
    }

    public List<String> getExcludes() {
        return excludes;
    }

    public List<PathBean> getRoots() {
        return roots;
    }

}

package com.hlops.mimas.core.data.bean.rootManager;

import com.hlops.mimas.core.config.VersionConfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 7/15/13
 * Time: 7:25 PM
 */
@XmlRootElement(name = "rootManager")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootManagerBean extends VersionConfig {

    @XmlElement(name = "path")
    private PathBean pathBean;

    public RootManagerBean() {
    }

    public RootManagerBean(PathBean pathBean) {
        this.pathBean = pathBean;
    }

    public PathBean getPathBean() {
        return pathBean;
    }

    public void setPathBean(PathBean pathBean) {
        this.pathBean = pathBean;
    }
}

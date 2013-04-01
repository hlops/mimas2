package com.hlops.mimas.config;

import com.hlops.mimas.data.bean.Version;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 30.03.13
 * Time: 19:02
 * To change this template use File | Settings | File Templates.
 */
public abstract class VersionConfig {

    @XmlElement(required = true)
    private Version version = new Version();

    public Version getVersion() {
        return version;
    }

    protected void setVersion(Version version) {
        this.version = version;
    }
}

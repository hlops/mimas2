package com.hlops.mimas.core.config;

import com.hlops.mimas.core.data.bean.Version;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 30.03.13
 * Time: 19:02
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class VersionConfig {

    @XmlElement(required = true)
    private Version version = new Version();

    @XmlElement(required = false, defaultValue = "false")
    private Boolean recreateConfig;

    public Version getVersion() {
        return version;
    }

    protected void setVersion(Version version) {
        this.version = version;
    }

    public boolean getRecreateConfig() {
        return recreateConfig == null || recreateConfig;
    }

}

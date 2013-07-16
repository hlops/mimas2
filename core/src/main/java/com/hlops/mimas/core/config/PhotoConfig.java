package com.hlops.mimas.core.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 30.03.13
 * Time: 19:09
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "photo")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhotoConfig extends VersionConfig {

    private String includedWildcard = "*.jpg,*.jpeg,*.gif,*.png";
    private String configName = "foto.xml";

    public String getIncludedWildcard() {
        return includedWildcard;
    }

    public String getConfigName() {
        return configName;
    }
}
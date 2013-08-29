package com.hlops.mimas.core.config;

import javax.xml.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 30.03.13
 * Time: 19:09
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"includedWildcard", "configFileName", "recreateConfig"})
public class PhotoConfig {

    private String includedWildcard = "*.jpg,*.jpeg,*.gif,*.png";
    private String configFileName = "foto.xml";

    @XmlElement(required = false, defaultValue = "false")
    private Boolean recreateConfig;

    public String getIncludedWildcard() {
        return includedWildcard;
    }

    public String getConfigFileName() {
        return configFileName;
    }

    public boolean isRecreateConfig() {
        return recreateConfig != null && recreateConfig;
    }
}

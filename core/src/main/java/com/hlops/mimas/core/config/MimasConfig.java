package com.hlops.mimas.core.config;

import javax.xml.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 27.03.13
 * Time: 22:37
 * To change this template use File | Settings | File Templates.
 */

@XmlRootElement(name = "mimas")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "dateFormat", "mimasFolderName", "syncConfig", "rootConfig", "photoConfig"
})
@SuppressWarnings("FieldCanBeLocal")
public class MimasConfig extends VersionConfig {

    @XmlElement(required = true, defaultValue = "dd.MM.YYY hh:mm:ss")
    private String dateFormat = "dd.MM.YYY hh:mm:ss";

    @XmlElement(required = true)
    private String mimasFolderName = ".mimas";

    @XmlElement(required = true)
    private SyncConfig syncConfig = new SyncConfig();

    @XmlElement(required = true)
    private PhotoConfig photoConfig = new PhotoConfig();

    @XmlElement(name = "managerConfig", required = true)
    private RootConfig rootConfig = new RootConfig();

    public MimasConfig() {
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public String getMimasFolderName() {
        return mimasFolderName;
    }

    public PhotoConfig getPhotoConfig() {
        return photoConfig;
    }

    public SyncConfig getSyncConfig() {
        return syncConfig;
    }

    public RootConfig getRootConfig() {
        return rootConfig;
    }
}

package com.hlops.mimas.core.config;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.*;
import java.net.URL;

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
        "dateFormat", "mimasFolderName", "syncConfig", "rootManagerConfig", "photoConfig"
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
    private RootManagerConfig rootManagerConfig = new RootManagerConfig();

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

    public RootManagerConfig getRootManagerConfig() {
        return rootManagerConfig;
    }
}

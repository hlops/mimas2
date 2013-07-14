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
@SuppressWarnings("FieldCanBeLocal")
public class MimasConfig extends VersionConfig {

    @XmlElement(required = true)
    private String dateFormat;

    @XmlElement(required = true)
    private String defaultMimasFolder = ".mimas";

    @XmlElement(required = true)
    private PhotoConfig photoConfig = new PhotoConfig();

    @XmlElement(required = true, defaultValue = "4")
    private int queueExecutors = 4;


    @XmlTransient
    private static MimasConfig instance = createMimasConfig();

    private static MimasConfig createMimasConfig() {
        try {
            JAXBContext jc = JAXBContext.newInstance(MimasConfig.class);
            URL resource = MimasConfig.class.getResource("/mimas.xml");
            if (resource == null) {
                throw new RuntimeException("Could not find configuration");
            }
            return (MimasConfig) jc.createUnmarshaller().unmarshal(resource);
        } catch (JAXBException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static MimasConfig getInstance() {
        return instance;
    }

    private MimasConfig() {
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public String getDefaultMimasFolder() {
        return defaultMimasFolder;
    }

    public PhotoConfig getPhotoConfig() {
        return photoConfig;
    }

    public int getQueueExecutors() {
        return queueExecutors;
    }

}

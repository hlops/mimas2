package com.hlops.mimas.core;

import com.hlops.mimas.core.config.MimasConfig;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 9/6/13
 * Time: 7:40 PM
 */
public class Mimas {

    private static AtomicReference<MimasConfig> mimasConfig = new AtomicReference<>();

    private Mimas() {
    }

    private static MimasConfig loadMimasConfig() {
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

    public static MimasConfig getConfig() {
        MimasConfig config = mimasConfig.get();
        if (config == null) {
            mimasConfig.compareAndSet(null, config = loadMimasConfig());
        }
        return config;
    }

}

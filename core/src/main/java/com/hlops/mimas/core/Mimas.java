package com.hlops.mimas.core;

import com.hlops.mimas.core.config.MimasConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 9/6/13
 * Time: 7:40 PM
 */
public class Mimas {

    private static Logger logger = LoggerFactory.getLogger(Mimas.class);

    private static AtomicReference<MimasConfig> mimasConfig = new AtomicReference<>();
    private static List<MimasConfigFileProvider> providers = new ArrayList<>();

    static {
        providers.add(new MimasConfigFileProvider(1000) {
            @Override
            public URL getResource() {
                String cfg = System.getProperty("mimas.config");
                return cfg == null ? null : MimasConfig.class.getResource(cfg);
            }
        });
    }

    private Mimas() {
        // hide constructor
    }

    public static MimasConfig getConfig() {
        MimasConfig config = mimasConfig.get();
        if (config == null) {
            mimasConfig.compareAndSet(null, config = loadMimasConfig());
        }
        return config;
    }

    public static void registerMimasFileProvider(MimasConfigFileProvider fileProvider) {
        providers.add(fileProvider);
    }

    private static MimasConfig loadMimasConfig() {
        try {
            JAXBContext jc = JAXBContext.newInstance(MimasConfig.class);
            URL resource = findResource();
            if (resource == null) {
                throw new RuntimeException("Could not find configuration");
            }
            return (MimasConfig) jc.createUnmarshaller().unmarshal(resource);
        } catch (JAXBException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static URL findResource() {
        Collections.sort(providers);
        for (MimasConfigFileProvider provider : providers) {
            try {
                URL resource = provider.getResource();
                if (resource != null) {
                    return resource;
                }
            } catch (NullPointerException e) {
                // do nothing
            } catch (MalformedURLException e) {
                logger.error(e.getMessage(), e);
            }
        }
        throw new RuntimeException("No mimas config file was found");
    }

}

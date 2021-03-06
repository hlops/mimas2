package com.hlops.mimas.servlet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.ServletModule;
import com.hlops.mimas.core.Mimas;
import com.hlops.mimas.core.MimasConfigFileProvider;
import com.hlops.mimas.core.inject.CoreGuiceModule;
import com.hlops.mimas.core.inject.DisposableModule;
import com.hlops.mimas.inject.ServiceGuiceModule;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory;
import com.sun.jersey.spi.container.WebApplication;
import com.sun.jersey.spi.container.servlet.ServletContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 14.07.13
 * Time: 21:22
 * To change this template use File | Settings | File Templates.
 */
public class GuiceServletContainer extends ServletContainer {

    private Injector injector;
    private List<DisposableModule> disposableModules = new ArrayList<DisposableModule>();

    public class ServletGuiceComponentProviderFactory extends GuiceComponentProviderFactory {
        public ServletGuiceComponentProviderFactory(ResourceConfig config, Injector injector) {
            super(config, injector);
        }
    }

    @Override
    protected void initiate(final ResourceConfig config, final WebApplication webapp) {
        Mimas.registerMimasFileProvider(new MimasConfigFileProvider(100) {
            @Override
            public URL getResource() throws MalformedURLException {
                return new File(System.getProperty("catalina.home") + "/conf/mimas/mimas.xml").toURI().toURL();
            }
        });
        injector = Guice.createInjector(
                new ServletModule() {
                    @Override
                    protected void configureServlets() {
                        install(new CoreGuiceModule());
                        install(new ServiceGuiceModule());
                    }

                    @Override
                    protected void install(Module module) {
                        super.install(module);

                        if (module instanceof DisposableModule) {
                            disposableModules.add((DisposableModule) module);
                        }
                    }
                });
        webapp.initiate(config, new ServletGuiceComponentProviderFactory(config, injector));
    }

    @Override
    public void destroy() {
        for (DisposableModule module : disposableModules) {
            module.dispose(injector);
        }
        super.destroy();
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.service(request, response);
    }

    protected Injector getInjector() {
        return injector;
    }
}

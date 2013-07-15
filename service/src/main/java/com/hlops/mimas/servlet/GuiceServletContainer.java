package com.hlops.mimas.servlet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scope;
import com.google.inject.servlet.ServletModule;
import com.google.inject.servlet.ServletScopes;
import com.hlops.mimas.core.inject.CoreGuiceModule;
import com.hlops.mimas.inject.ServiceGuiceModule;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory;
import com.sun.jersey.spi.container.WebApplication;
import com.sun.jersey.spi.container.servlet.ServletContainer;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 14.07.13
 * Time: 21:22
 * To change this template use File | Settings | File Templates.
 */
public class GuiceServletContainer extends ServletContainer {

    public class ServletGuiceComponentProviderFactory extends GuiceComponentProviderFactory {
        public ServletGuiceComponentProviderFactory(ResourceConfig config, Injector injector) {
            super(config, injector);
        }

        @Override
        public Map<Scope, ComponentScope> createScopeMap() {
            Map<Scope, ComponentScope> m = super.createScopeMap();

            m.put(ServletScopes.REQUEST, ComponentScope.PerRequest);
            return m;
        }
    }

    @Override
    protected void initiate(final ResourceConfig config, final WebApplication webapp) {
        Injector injector = Guice.createInjector(
                new ServletModule() {
                    @Override
                    protected void configureServlets() {
                        install(new CoreGuiceModule());
                        install(new ServiceGuiceModule());
                    }
                });
        webapp.initiate(config, new ServletGuiceComponentProviderFactory(config, injector));
    }

}

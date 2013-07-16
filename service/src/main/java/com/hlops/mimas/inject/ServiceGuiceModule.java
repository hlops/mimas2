package com.hlops.mimas.inject;

import com.google.inject.AbstractModule;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 7/15/13
 * Time: 7:23 PM
 */
public class ServiceGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        //bind(RootManager.class).to(RootManagerImpl.class).in(Scopes.SINGLETON);
    }

}

package com.hlops.mimas.core.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.hlops.mimas.core.service.CacheService;
import com.hlops.mimas.core.service.ImageService;
import com.hlops.mimas.core.service.QueueService;
import com.hlops.mimas.core.service.impl.CacheServiceImpl;
import com.hlops.mimas.core.service.impl.ImageServiceImpl;
import com.hlops.mimas.core.service.impl.QueueServiceImpl;
import com.hlops.mimas.core.service.photo.PhotoService;
import com.hlops.mimas.core.service.photo.impl.PhotoServiceImpl;
import com.hlops.mimas.core.service.tv.TVService;
import com.hlops.mimas.core.service.tv.impl.TVServiceImpl;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 29.03.13
 * Time: 22:20
 * To change this template use File | Settings | File Templates.
 */
public class CoreGuiceModule extends AbstractModule implements Disposable {

    @Override
    protected void configure() {
        bind(ImageService.class).to(ImageServiceImpl.class).in(Scopes.SINGLETON);
        bind(PhotoService.class).to(PhotoServiceImpl.class).in(Scopes.SINGLETON);

        bind(CacheService.class).to(CacheServiceImpl.class).in(Scopes.SINGLETON);
        bind(QueueService.class).to(QueueServiceImpl.class).in(Scopes.SINGLETON);

        bind(TVService.class).to(TVServiceImpl.class).in(Scopes.SINGLETON);
    }

    @Override
    public void dispose(Injector injector) {
        injector.getProvider(QueueService.class).get().dispose();
    }
}

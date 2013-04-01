package com.hlops.mimas.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.hlops.mimas.service.CacheService;
import com.hlops.mimas.service.ImageService;
import com.hlops.mimas.service.PhotoAlbumService;
import com.hlops.mimas.service.QueueService;
import com.hlops.mimas.service.impl.CacheServiceImpl;
import com.hlops.mimas.service.impl.ImageServiceImpl;
import com.hlops.mimas.service.impl.PhotoAlbumServiceImpl;
import com.hlops.mimas.service.impl.QueueServiceImpl;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 29.03.13
 * Time: 22:20
 * To change this template use File | Settings | File Templates.
 */
public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ImageService.class).to(ImageServiceImpl.class).in(Scopes.SINGLETON);
        bind(PhotoAlbumService.class).to(PhotoAlbumServiceImpl.class).in(Scopes.SINGLETON);

        bind(CacheService.class).to(CacheServiceImpl.class).in(Scopes.SINGLETON);
        bind(QueueService.class).to(QueueServiceImpl.class).in(Scopes.SINGLETON);
    }
}

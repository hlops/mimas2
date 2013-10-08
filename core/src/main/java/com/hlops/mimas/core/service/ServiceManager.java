package com.hlops.mimas.core.service;

import com.google.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 07.10.13
 * Time: 21:06
 * To change this template use File | Settings | File Templates.
 */
public class ServiceManager {

    @Inject
    private QueueService queueService;

    @Inject
    private RootManagerService rootManagerService;

    @Inject
    private ImageService imageService;

    @Inject
    private CacheService cacheService;

    public QueueService getQueueService() {
        return queueService;
    }

    public RootManagerService getRootManagerService() {
        return rootManagerService;
    }

    public ImageService getImageService() {
        return imageService;
    }

    public CacheService getCacheService() {
        return cacheService;
    }
}

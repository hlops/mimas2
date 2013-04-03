package com.hlops.mimas.service;

import com.hlops.mimas.data.EntityKey;
import com.hlops.mimas.data.key.photo.PhotoAlbumKey;
import com.hlops.mimas.sync.EntityKeyFuture;
import com.hlops.mimas.sync.TaskFactory;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 31.03.13
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */
public interface QueueService {

    <T extends EntityKeyFuture> T get(EntityKey key, TaskFactory<T> factory);
}

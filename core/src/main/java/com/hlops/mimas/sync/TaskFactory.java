package com.hlops.mimas.sync;

import com.hlops.mimas.data.EntityKey;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 03.04.13
 * Time: 22:28
 * To change this template use File | Settings | File Templates.
 */
public interface TaskFactory<T extends EntityKeyFuture> {

    T create(EntityKey key);
}

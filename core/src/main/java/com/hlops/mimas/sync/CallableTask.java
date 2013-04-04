package com.hlops.mimas.sync;

import com.hlops.mimas.data.EntityKey;

import java.util.concurrent.Callable;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 1/30/13
 * Time: 4:43 PM
 */
public interface CallableTask<Key extends EntityKey, T> extends Callable<T> {

    Key getKey();

}

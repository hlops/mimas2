package com.hlops.mimas.core.sync;

import java.util.concurrent.Callable;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 1/30/13
 * Time: 4:43 PM
 */
public interface CallableTask<T> extends Callable<T>, AbstractTask {

}

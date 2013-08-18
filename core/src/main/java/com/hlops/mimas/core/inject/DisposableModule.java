package com.hlops.mimas.core.inject;

import com.google.inject.Injector;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 12.08.13
 * Time: 0:55
 * To change this template use File | Settings | File Templates.
 */
public interface DisposableModule {

    void dispose(Injector injector);
}

package com.hlops.mimas.core.service.rootManager;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 07.09.13
 * Time: 17:52
 * To change this template use File | Settings | File Templates.
 */
public class RootManagerException extends Exception {

    public RootManagerException() {
    }

    public RootManagerException(String message) {
        super(message);
    }

    public RootManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public RootManagerException(Throwable cause) {
        super(cause);
    }

    public RootManagerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

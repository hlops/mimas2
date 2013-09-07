package com.hlops.mimas.core.service.rootManager;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 07.09.13
 * Time: 17:52
 * To change this template use File | Settings | File Templates.
 */
public class RootException extends Exception {

    public RootException() {
    }

    public RootException(String message) {
        super(message);
    }

    public RootException(String message, Throwable cause) {
        super(message, cause);
    }

    public RootException(Throwable cause) {
        super(cause);
    }

    public RootException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

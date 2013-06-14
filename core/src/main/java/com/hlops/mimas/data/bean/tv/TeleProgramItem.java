package com.hlops.mimas.data.bean.tv;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 09.05.13
 * Time: 20:26
 * To change this template use File | Settings | File Templates.
 */
public class TeleProgramItem {

    private final Date date;
    private final String message;

    public TeleProgramItem(String message, Date date) {
        this.message = message;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }
}

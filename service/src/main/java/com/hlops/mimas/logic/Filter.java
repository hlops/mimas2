package com.hlops.mimas.logic;

import org.apache.commons.lang.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 05.10.13
 * Time: 0:09
 * To change this template use File | Settings | File Templates.
 */
public abstract class Filter<T> {

    @SuppressWarnings("FieldCanBeLocal")
    private final String query;

    protected Filter(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public abstract boolean accept(T bean);

    @SuppressWarnings("SimplifiableIfStatement")
    protected boolean like(String text) {
        if (StringUtils.isEmpty(query)) {
            return true;
        }
        if (StringUtils.isEmpty(text)) {
            return false;
        }
        return text.toLowerCase().contains(query.toLowerCase());
    }
}

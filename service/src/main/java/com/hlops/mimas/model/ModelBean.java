package com.hlops.mimas.model;

import com.hlops.mimas.model.leftMenu.LeftMenuBean;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 25.06.13
 * Time: 19:08
 * To change this template use File | Settings | File Templates.
 */
public abstract class ModelBean {

    private LeftMenuBean menu;

    protected ModelBean() {
        this.menu = createLeftMenu();
    }

    public LeftMenuBean getMenu() {
        return menu;
    }

    protected abstract LeftMenuBean createLeftMenu();
}

package com.hlops.mimas.model.leftMenu.impl;

import com.hlops.mimas.model.leftMenu.MenuGroupBean;
import com.hlops.mimas.model.leftMenu.MenuItemBean;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 28.06.13
 * Time: 0:39
 * To change this template use File | Settings | File Templates.
 */
public class LeftMenuOrder extends MenuGroupBean {

    public LeftMenuOrder() {
        super("leftMenuOrderBy");
        getItems().add(new MenuItemBean(getId() + "Abc"));
        getItems().add(new MenuItemBean(getId() + "Date"));
    }
}

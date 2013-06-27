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
public class LeftMenuView extends MenuGroupBean {

    public LeftMenuView() {
        super("leftMenuOrderView");
        getItems().add(new MenuItemBean(getId() + "Tile"));
        getItems().add(new MenuItemBean(getId() + "List"));
        getItems().add(new MenuItemBean(getId() + "Details"));
    }
}

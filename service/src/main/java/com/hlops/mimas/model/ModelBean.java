package com.hlops.mimas.model;

import com.hlops.mimas.model.leftMenu.MenuItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 25.06.13
 * Time: 19:08
 * To change this template use File | Settings | File Templates.
 */
public abstract class ModelBean {

    private List<MenuItemBean> leftMenu = new ArrayList<MenuItemBean>();

    protected ModelBean() {
        createLeftMenu(leftMenu);
    }

    public List<MenuItemBean> getLeftMenu() {
        return leftMenu;
    }

    protected abstract void createLeftMenu(List<MenuItemBean> leftMenu);
}

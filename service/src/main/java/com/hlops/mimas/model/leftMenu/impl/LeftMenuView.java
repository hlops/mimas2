package com.hlops.mimas.model.leftMenu.impl;

import com.hlops.mimas.model.leftMenu.MenuBean;
import com.hlops.mimas.model.leftMenu.MenuGroupBean;
import com.hlops.mimas.model.leftMenu.MenuItemBean;
import com.hlops.mimas.utils.CookieProvider;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 28.06.13
 * Time: 0:39
 * To change this template use File | Settings | File Templates.
 */
public class LeftMenuView extends MenuGroupBean implements MenuBean {

    public LeftMenuView(String alias, CookieProvider cookieProvider) {
        super(null, "leftMenuView", alias + "V", cookieProvider);
        getItems().add(new MenuItemBean(this, "Tile", "T"));
        getItems().add(new MenuItemBean(this, "List", "L"));
        getItems().add(new MenuItemBean(this, "Details", "D"));
    }

    @Override
    public String[] listI18nTemplates() {
        return new String[]{"leftMenu"};
    }
}

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
public class LeftMenuOrder extends MenuGroupBean implements MenuBean {

    public static final String ID = "leftMenuOrderBy";

    public LeftMenuOrder(CookieProvider cookieProvider) {
        super(ID, cookieProvider);
        getItems().add(new MenuItemBean(getId() + "Abc"));
        getItems().add(new MenuItemBean(getId() + "Date"));
    }

    @Override
    public String[] listI18nTemplates() {
        return new String[]{"leftMenu"};
    }

    public boolean isAsc() {
        return getSelected() != null && getSelected().equals(getId() + "Abc");
    }
}

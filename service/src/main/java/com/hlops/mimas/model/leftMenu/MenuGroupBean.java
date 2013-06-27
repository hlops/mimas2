package com.hlops.mimas.model.leftMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 25.06.13
 * Time: 19:10
 * To change this template use File | Settings | File Templates.
 */
public class MenuGroupBean extends MenuItemBean {

    private List<MenuItemBean> items = new ArrayList<MenuItemBean>();

    public MenuGroupBean(String id) {
        super(id);
    }

    public List<MenuItemBean> getItems() {
        return items;
    }

}

package com.hlops.mimas.model.leftMenu;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 25.06.13
 * Time: 19:11
 * To change this template use File | Settings | File Templates.
 */
public class MenuItemBean extends AbsractMenuItemBean {

    private String id;

    public MenuItemBean(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

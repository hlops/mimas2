package com.hlops.mimas.model.leftMenu;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 25.06.13
 * Time: 19:09
 * To change this template use File | Settings | File Templates.
 */
public class LeftMenuBean {

    @XmlList
    private List<MenuItemBean> items = new ArrayList<MenuItemBean>();

    public List<MenuItemBean> getItems() {
        return items;
    }

}

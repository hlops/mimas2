package com.hlops.mimas.model.leftMenu;

import com.hlops.mimas.utils.CookieProvider;
import org.jetbrains.annotations.Nullable;

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

    private String selected;

    private List<MenuItemBean> items = new ArrayList<MenuItemBean>() {
        @Override
        public boolean add(MenuItemBean menuItemBean) {
            final boolean add = super.add(menuItemBean);
            if (selected == null) {
                selected = menuItemBean.getId();
            }
            return add;
        }

        @Override
        public void add(int index, MenuItemBean element) {
            super.add(index, element);
            if (selected == null) {
                selected = element.getId();
            }
        }
    };

    public MenuGroupBean(String id) {
        this(id, null);
    }

    public MenuGroupBean(String id, @Nullable CookieProvider cookieProvider) {
        super(id);
        if (cookieProvider != null) {
            selected = cookieProvider.getCookieValue(id);
        }
    }

    public List<MenuItemBean> getItems() {
        return items;
    }

    @Nullable
    public String getSelected() {
        return selected;
    }

    public void setSelected(@Nullable String selected) {
        this.selected = selected;
    }

}

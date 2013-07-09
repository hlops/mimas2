package com.hlops.mimas.model;

import com.hlops.mimas.model.leftMenu.MenuBean;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 25.06.13
 * Time: 19:08
 * To change this template use File | Settings | File Templates.
 */
public abstract class ModelBean {

    private List<MenuBean> leftMenu = null;
    protected Set<String> i18n = new HashSet<String>();

    protected ModelBean() {
        i18n.add("site");
        leftMenu = new ArrayList<MenuBean>();
        createLeftMenu(leftMenu);
        for (MenuBean bean : leftMenu) {
            i18n.addAll(Arrays.asList(bean.listI18nTemplates()));
        }
    }

    public List<MenuBean> getLeftMenu() {
        return leftMenu;
    }

    protected abstract void createLeftMenu(List<MenuBean> leftMenu);

    public Set<String> getI18n() {
        return i18n;
    }
}

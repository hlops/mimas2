package com.hlops.mimas.model.projects;

import com.hlops.mimas.model.ModelBean;
import com.hlops.mimas.model.leftMenu.MenuBean;
import com.hlops.mimas.model.leftMenu.impl.LeftMenuOrder;
import com.hlops.mimas.model.leftMenu.impl.LeftMenuView;
import com.hlops.mimas.utils.CookieProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.annotation.XmlList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 24.06.13
 * Time: 1:27
 * To change this template use File | Settings | File Templates.
 */
public class ProjectsBean extends ModelBean {

    public static final String ID = LEFT_MENU_ID_PREFIX + "Ph";

    @XmlList
    private List<ProjectItemBean> projects;

    public ProjectsBean(CookieProvider cookieProvider) {
        super(cookieProvider);
        projects = new ArrayList<ProjectItemBean>();
    }

    public List<ProjectItemBean> getProjects() {
        return projects;
    }

    @Override
    protected void createLeftMenu(CookieProvider cookieProvider, List<MenuBean> leftMenu) {
        leftMenu.add(new LeftMenuOrder(ID, cookieProvider));
        leftMenu.add(new LeftMenuView(ID, cookieProvider));
    }

    @Nullable
    public MenuBean getLeftMenuItem(@NotNull String id) {
        for (MenuBean bean : getLeftMenu()) {
            if (id.equals(bean.getId())) {
                return bean;
            }
        }
        return null;
    }
}

package com.hlops.mimas.model.projects;

import com.hlops.mimas.model.ModelBean;
import com.hlops.mimas.model.leftMenu.MenuBean;
import com.hlops.mimas.model.leftMenu.impl.LeftMenuOrder;
import com.hlops.mimas.model.leftMenu.impl.LeftMenuView;

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

    @XmlList
    private List<ProjectItemBean> projects;

    public ProjectsBean() {
        projects = new ArrayList<ProjectItemBean>();
    }

    public List<ProjectItemBean> getProjects() {
        return projects;
    }

    @Override
    protected void createLeftMenu(List<MenuBean> leftMenu) {
        leftMenu.add(new LeftMenuOrder());
        leftMenu.add(new LeftMenuView());
    }
}

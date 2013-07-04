package com.hlops.mimas.model.projects;

import com.hlops.mimas.model.ModelBean;
import com.hlops.mimas.model.leftMenu.LeftMenuBean;
import com.hlops.mimas.model.leftMenu.MenuItemBean;
import com.hlops.mimas.model.leftMenu.impl.LeftMenuOrder;
import com.hlops.mimas.model.leftMenu.impl.LeftMenuView;

import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
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
        projects.add(new ProjectItemBean("project1", "aaa"));
        projects.add(new ProjectItemBean("project2", "bb"));
        projects.add(new ProjectItemBean("project3", "cccc"));
        projects.add(new ProjectItemBean("project4", "ddddddd"));
        projects.add(new ProjectItemBean("project5", "e ee e eeee!"));
    }

    public List<ProjectItemBean> getProjects() {
        return projects;
    }

    @Override
    protected void createLeftMenu(List<MenuItemBean> leftMenu) {
        leftMenu.add(new LeftMenuOrder());
        leftMenu.add(new LeftMenuView());
    }
}

package com.hlops.mimas.model.projects;

import com.hlops.mimas.model.ModelBean;
import com.hlops.mimas.model.leftMenu.LeftMenuBean;
import com.hlops.mimas.model.leftMenu.MenuGroupBean;
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
@XmlRootElement
public class ProjectsBean extends ModelBean {

    @XmlList
    private List<ProjectBean> projects;

    public ProjectsBean() {
        projects = new ArrayList<ProjectBean>();
        projects.add(new ProjectBean("project1"));
        projects.add(new ProjectBean("project2"));
        projects.add(new ProjectBean("project3"));
        projects.add(new ProjectBean("project4"));
        projects.add(new ProjectBean("project5"));
    }

    public List<ProjectBean> getProjects() {
        return projects;
    }

    @Override
    protected LeftMenuBean createLeftMenu() {
        LeftMenuBean leftMenuBean = new LeftMenuBean();
        leftMenuBean.getItems().add(new LeftMenuOrder());
        leftMenuBean.getItems().add(new LeftMenuView());
        return leftMenuBean;
    }
}

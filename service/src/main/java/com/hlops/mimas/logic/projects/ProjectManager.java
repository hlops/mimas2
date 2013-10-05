package com.hlops.mimas.logic.projects;

import com.hlops.mimas.logic.Filter;
import com.hlops.mimas.model.projects.ProjectItemBean;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 04.10.13
 * Time: 22:58
 * To change this template use File | Settings | File Templates.
 */
public class ProjectManager {

    private static final ProjectManager instance = new ProjectManager();

    private final List<ProjectItemBean> projects;
    private final ResourceBundle bundle = ResourceBundle.getBundle("projects");

    public static ProjectManager getInstance() {
        return instance;
    }

    private ProjectManager() {
        projects = new ArrayList<>();
        projects.add(createProjectItemBean("photos"));
    }

    private ProjectItemBean createProjectItemBean(String id) {
        ProjectItemBean bean = new ProjectItemBean(id);
        bean.setName(bundle.getString("project." + id + ".name"));
        bean.setDescription(bundle.getString("project." + id + ".description"));
        return bean;
    }

    public void fillProjects(List<ProjectItemBean> list, Filter<ProjectItemBean> filter) {
        for (ProjectItemBean bean : projects) {
            if (filter.accept(bean)) {
                list.add(bean);
            }
        }
    }
}

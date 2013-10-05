package com.hlops.mimas.logic.projects;

import com.hlops.mimas.logic.Filter;
import com.hlops.mimas.model.projects.ProjectItemBean;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 05.10.13
 * Time: 0:26
 * To change this template use File | Settings | File Templates.
 */
public class ProjectsFilter extends Filter<ProjectItemBean> {

    public ProjectsFilter(String query) {
        super(query);
    }

    @Override
    public boolean accept(ProjectItemBean bean) {
        return like(bean.getName()) || like(bean.getDescription());
    }

}

package com.hlops.mimas.webService;

import com.hlops.mimas.logic.projects.ProjectManager;
import com.hlops.mimas.logic.projects.ProjectsFilter;
import com.hlops.mimas.model.leftMenu.impl.LeftMenuOrder;
import com.hlops.mimas.model.projects.ProjectItemBean;
import com.hlops.mimas.model.projects.ProjectsBean;
import com.hlops.mimas.utils.CookieProvider;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 24.06.13
 * Time: 1:14
 * To change this template use File | Settings | File Templates.
 */
@Path("/projects")
public class Project {

    @Context
    private HttpServletRequest httpRequest;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ProjectsBean list(@QueryParam("query") String query) {
        final ProjectsBean projectsBean = new ProjectsBean(new CookieProvider(httpRequest));
        final List<ProjectItemBean> projects = projectsBean.getProjects();

        ProjectManager.getInstance().fillProjects(projects, new ProjectsFilter(query));

        final LeftMenuOrder order = (LeftMenuOrder) projectsBean.getLeftMenuItem("lbPrOA");
        if (order != null && order.isAsc()) {
            Collections.reverse(projects);
        }
        return projectsBean;
    }

}

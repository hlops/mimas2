package com.hlops.mimas.service;

import com.hlops.mimas.model.projects.ProjectItemBean;
import com.hlops.mimas.model.projects.ProjectsBean;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ProjectsBean list(@QueryParam("query") String query) {
        final ProjectsBean projectsBean = new ProjectsBean();
        final List<ProjectItemBean> projects = projectsBean.getProjects();
        addFiltered(query, projects, "Project 1", "some text");
        addFiltered(query, projects, "Project 2", "some more text");
        addFiltered(query, projects, "Project 3", "some other text");
        addFiltered(query, projects, "Project 4", "different text");
        addFiltered(query, projects, "Project 5", "very different text");
        return projectsBean;
    }

    private void addFiltered(String query, List<ProjectItemBean> projects, String name, String description) {
        if (query == null || name.toLowerCase().contains(query.toLowerCase()) || description.toLowerCase().contains(query.toLowerCase())) {
            projects.add(new ProjectItemBean(name, description));
        }
    }
}

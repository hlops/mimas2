package com.hlops.mimas.service;

import com.hlops.mimas.model.projects.ProjectsBean;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
    public ProjectsBean list() {
        return new ProjectsBean();
    }
}

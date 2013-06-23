package com.hlops.mimas.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

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
    public String test() {
        return "Ok!";
    }
}

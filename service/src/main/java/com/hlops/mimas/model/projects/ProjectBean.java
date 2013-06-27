package com.hlops.mimas.model.projects;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 24.06.13
 * Time: 1:27
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class ProjectBean {

    private String name;

    public ProjectBean() {
    }

    public ProjectBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

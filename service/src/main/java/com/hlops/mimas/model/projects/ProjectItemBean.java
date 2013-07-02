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
public class ProjectItemBean {

    private String name;
    private String description;

    public ProjectItemBean(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

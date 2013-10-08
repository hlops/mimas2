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

    private final String id;
    private String name;
    private String description;

    public String getId() {
        return id;
    }

    public ProjectItemBean(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRef() {
        return id;
    }
}

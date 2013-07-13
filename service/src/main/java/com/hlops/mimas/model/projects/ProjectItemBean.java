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
    private String ref;

    public ProjectItemBean(String name, String description, String ref) {
        this.name = name;
        this.description = description;
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRef() {
        return ref;
    }
}

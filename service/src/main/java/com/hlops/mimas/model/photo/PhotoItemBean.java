package com.hlops.mimas.model.photo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 24.06.13
 * Time: 1:27
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class PhotoItemBean {

    private String name;
    private String description;
    private String ref;

    public PhotoItemBean(String name, String description, String ref) {
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

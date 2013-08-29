package com.hlops.mimas.core.config;

import com.hlops.mimas.core.data.bean.rootManager.PathBean;

import javax.xml.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 23.08.13
 * Time: 0:06
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
@XmlAccessorType(XmlAccessType.FIELD)
public class RootManagerConfig {

    @XmlElement(name = "path")
    private PathBean pathBean = new PathBean();
}

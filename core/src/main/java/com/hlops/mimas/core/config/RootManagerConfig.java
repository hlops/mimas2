package com.hlops.mimas.core.config;

import com.hlops.mimas.core.data.bean.rootManager.RootManagerBean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 23.08.13
 * Time: 0:06
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RootManagerConfig {

    private List<RootManagerBean> roots = new ArrayList<>();

    private Map<String, RootManagerBean> rootMap = new LinkedHashMap<>();

    @XmlElementWrapper(name = "roots")
    @XmlElement(name = "root")
    public List<RootManagerBean> getRoots() {
        return roots;
    }

    protected void setRoots(List<RootManagerBean> roots) {
        this.roots = roots;
        rootMap.clear();
        for (RootManagerBean bean : roots) {
            rootMap.put(bean.getId(), bean);
        }
    }

    @Transient
    public RootManagerBean getRoot(String id) {
        return rootMap.get(id);
    }
}

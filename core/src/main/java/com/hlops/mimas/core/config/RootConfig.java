package com.hlops.mimas.core.config;

import com.hlops.mimas.core.data.bean.rootManager.RootManagerBean;

import javax.xml.bind.annotation.*;
import java.beans.Transient;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 23.08.13
 * Time: 0:06
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("UnusedDeclaration")
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RootConfig {

    private List<RootManagerBean> roots;

    private Map<String, RootManagerBean> rootMap = new HashMap<>();

    @XmlElementWrapper(name = "roots")
    @XmlElement(name = "root")
    public List<RootManagerBean> getRoots() {
        return roots;
    }

    protected void setRoots(List<RootManagerBean> roots) {
        this.roots = Collections.unmodifiableList(roots);
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

package com.hlops.mimas.core.config;

import com.hlops.mimas.core.data.bean.rootManager.RootManagerBean;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

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

    @XmlElement()
    private List<RootManagerBean> roots = new ArrayList<>();

    public List<RootManagerBean> getRoots() {
        return roots;
    }
}

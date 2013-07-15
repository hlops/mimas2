package com.hlops.mimas.logic.root.bean;

import com.hlops.mimas.core.config.VersionConfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 7/15/13
 * Time: 7:25 PM
 */
@XmlRootElement(name = "RootManager")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootManagerBean extends VersionConfig {

    private List<RootBean> roots = new ArrayList<RootBean>();

    public List<RootBean> getRoots() {
        return roots;
    }
}

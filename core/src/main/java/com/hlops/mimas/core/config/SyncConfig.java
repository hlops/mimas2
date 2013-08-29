package com.hlops.mimas.core.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 23.08.13
 * Time: 0:06
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"queueExecutors"})
public class SyncConfig {

    @XmlElement(required = true, defaultValue = "4")
    private int queueExecutors = 4;

    public int getQueueExecutors() {
        return queueExecutors;
    }

    public void setQueueExecutors(int queueExecutors) {
        this.queueExecutors = queueExecutors;
    }
}

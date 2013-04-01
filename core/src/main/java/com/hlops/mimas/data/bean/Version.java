package com.hlops.mimas.data.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 30.03.13
 * Time: 17:20
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "version")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Version implements Comparable<Version> {

    private int major = 1;
    private int minor;
    private int patch;

    public Version() {
    }

    public Version(String version) {
        setVersion(version);
    }

    @XmlValue()
    public String getVersion() {
        return major + "." + minor + "." + patch;
    }

    public void setVersion(String version) {
        String[] arr = version.split("\\.");
        try {
            major = arr.length > 0 ? checkValue(Integer.parseInt(arr[0])) : 1;
            minor = arr.length > 1 ? checkValue(Integer.parseInt(arr[1])) : 0;
            patch = arr.length > 2 ? checkValue(Integer.parseInt(arr[2])) : 0;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid version: " + version);
        }
    }

    private int checkValue(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        return n;
    }

    public boolean isCompatible(Version v) {
        return compareTo(v) >= 0;
    }

    public int compareTo(Version v) {
        int n = Integer.compare(v.major, major);
        if (n == 0) {
            n = Integer.compare(v.minor, minor);
        }
        if (n == 0) {
            n = Integer.compare(v.patch, patch);
        }
        return n;
    }
}

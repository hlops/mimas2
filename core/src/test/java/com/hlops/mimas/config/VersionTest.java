package com.hlops.mimas.config;

import com.hlops.mimas.data.bean.Version;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 30.03.13
 * Time: 17:26
 * To change this template use File | Settings | File Templates.
 */
public class VersionTest extends Assert {

    @Test
    public void testVersion() throws Exception {
        Version version = new Version("5.12.1802");
        assertEquals(version.getVersion(), "5.12.1802");
    }

    @Test
    public void testVersionCompare() throws Exception {

        assertEquals(0, new Version("1.2.3").compareTo(new Version("1.2.3")));

        Version[] versions = new Version[]{
                new Version("0.0.1"),
                new Version("0.1.0"),
                new Version("0.1.1"),
                new Version("1.0.0"),
                new Version("1.0.1"),
                new Version("1.1.0"),
                new Version("1.1.1")
        };

        for (int i = 0; i < versions.length; i++) {
            for (int n = 0; n < i; n++) {
                assertEquals(-1, versions[i].compareTo(versions[n]));
            }
            for (int n = i + 1; n < versions.length; n++) {
                assertEquals(1, versions[i].compareTo(versions[n]));
            }
        }
    }
}

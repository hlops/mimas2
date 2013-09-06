package com.hlops.mimas.core.data.bean.rootManager;

import com.hlops.mimas.core.config.Mimas;
import com.hlops.mimas.core.config.MimasConfig;
import junit.framework.TestCase;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 7/16/13
 * Time: 7:20 PM
 */
public class RootManagerBeanTest extends TestCase {

    public void testName() throws Exception {
        final MimasConfig instance = Mimas.getConfig();
        System.out.println(instance.getRootManagerConfig().getRoot("foto"));
    }
}

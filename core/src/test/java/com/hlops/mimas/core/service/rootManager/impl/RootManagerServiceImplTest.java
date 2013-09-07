package com.hlops.mimas.core.service.rootManager.impl;

import com.google.inject.Inject;
import com.hlops.mimas.core.inject.CoreGuiceModule;
import com.hlops.mimas.core.service.rootManager.RootManagerService;
import com.hlops.mimas.test.GuiceJUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 07.09.13
 * Time: 17:25
 * To change this template use File | Settings | File Templates.
 */
@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({CoreGuiceModule.class})
public class RootManagerServiceImplTest {

    @Inject
    private RootManagerService rootManagerService;

    @Test
    public void testGetFile() throws Exception {
        rootManagerService.getFile("test1/.//test1.jpg");
    }
}

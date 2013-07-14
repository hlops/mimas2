package com.hlops.mimas.test.core.service.tv.impl;

import com.google.inject.Inject;
import com.hlops.mimas.core.data.bean.tv.TeleProgramItem;
import com.hlops.mimas.core.module.ServiceModule;
import com.hlops.mimas.core.service.tv.TVService;
import com.hlops.mimas.test.GuiceJUnitRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 09.05.13
 * Time: 20:39
 * To change this template use File | Settings | File Templates.
 */
@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({ServiceModule.class})
public class TVServiceImplTest extends Assert {

    @Inject
    private TVService tvService;

    @Test
    public void testLoad() throws Exception {
        ZipFile zipFile = new ZipFile("D:\\Distr\\Downloads\\schedule.zip");
        ZipEntry entry1 = zipFile.getEntry("10.ndx");
        ZipEntry entry2 = zipFile.getEntry("10.pdt");

        List<TeleProgramItem> list = tvService.load(zipFile.getInputStream(entry1), zipFile.getInputStream(entry2));
        tvService.save2XMLTV(list, System.out);
    }
}

package com.hlops.mimas.test.core.service;

import com.google.inject.Inject;
import com.hlops.mimas.core.module.ServiceModule;
import com.hlops.mimas.core.service.ImageService;
import com.hlops.mimas.core.service.Size;
import com.hlops.mimas.test.GuiceJUnitRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 29.03.13
 * Time: 22:13
 * To change this template use File | Settings | File Templates.
 */

@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({ServiceModule.class})
public class ImageServiceTest extends Assert {

    @Inject
    private ImageService imageService;

    @Test
    public void testGetScale() throws Exception {
        Assert.assertEquals(Size.positive(100, 100), imageService.getScale(Size.positive(100, 100), Size.positive(100, 100)));
        assertEquals(Size.positive(10, 10), imageService.getScale(Size.positive(100, 100), Size.positive(100, 10)));
        assertEquals(Size.positive(10, 10), imageService.getScale(Size.positive(100, 100), Size.positive(10, 100)));
        assertEquals(Size.positive(10, 10), imageService.getScale(Size.positive(100, 100), Size.positive(10, 10)));

        assertEquals(Size.positive(10, 1), imageService.getScale(Size.positive(100, 10), Size.positive(10, 1)));
        assertEquals(Size.positive(1, 10), imageService.getScale(Size.positive(10, 100), Size.positive(1, 10)));

        assertEquals(Size.positive(20, 10), imageService.getScale(Size.positive(200, 100), Size.positiveOrZero(20, 0)));
        assertEquals(Size.positive(40, 20), imageService.getScale(Size.positive(200, 100), Size.positiveOrZero(0, 20)));
    }

    @Test
    public void testResizeTo() throws Exception {
        File source = new File("core/src/test/resources/foto/testFoto1.jpg");
        assertTrue(source.exists());
        File dest = new File("core/target/test1.jpg");
        long t = System.currentTimeMillis();
        imageService.resizeTo(source, dest, Size.positiveOrZero(100, 0));
        assertTrue(dest.lastModified() > t);
        assertEquals(Size.positive(100, 75), imageService.getImageSize(dest));
    }
}

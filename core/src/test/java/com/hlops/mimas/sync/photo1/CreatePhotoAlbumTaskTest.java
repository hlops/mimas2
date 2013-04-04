package com.hlops.mimas.sync.photo1;

import com.google.inject.Inject;
import com.hlops.mimas.data.key.photo.PhotoAlbumKey;
import com.hlops.mimas.module.ServiceModule;
import com.hlops.mimas.service.photo.PhotoService;
import com.hlops.mimas.test.GuiceJUnitRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 01.04.13
 * Time: 23:35
 * To change this template use File | Settings | File Templates.
 */
@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({ServiceModule.class})
public class CreatePhotoAlbumTaskTest extends Assert {

    @Inject
    private PhotoService photoService;

    class TestThread extends Thread {
        private PhotoAlbumKey albumKey;

        TestThread(ThreadGroup threadGroup, String name, PhotoAlbumKey albumKey) {
            super(threadGroup, name);
            this.albumKey = albumKey;
        }

        @Override
        public void run() {
            try {
                System.out.println(photoService.getAlbum(albumKey));
            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }
    }

    @Test
    public void testGetAlbum() throws Exception {

        File path = new File("core/src/test/resources/foto/2");
        PhotoAlbumKey albumKey = new PhotoAlbumKey(path);

        TestThread[] threads = new TestThread[20];

        ThreadGroup threadGroup = new ThreadGroup("test");
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new TestThread(threadGroup, "thread#" + i, albumKey);
        }

        long t = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(((System.currentTimeMillis() - t) / 100 / 10.));
/*
        PhotoAlbum album = CreatePhotoAlbumTask.getAlbum(new PhotoAlbumKey(path));
*/

    }
}

package com.hlops.mimas.test.core.sync.photo1;

import com.google.inject.Inject;
import com.hlops.mimas.core.data.key.photo.PhotoAlbumKey;
import com.hlops.mimas.core.inject.CoreGuiceModule;
import com.hlops.mimas.core.service.photo.PhotoService;
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
@GuiceJUnitRunner.GuiceModules({CoreGuiceModule.class})
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
                System.out.println("loaded album " + photoService.getAlbum(albumKey).getName());
            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }
    }

    @Test
    public void testGetAlbum() throws Exception {

        TestThread[] threads = new TestThread[20];

        ThreadGroup threadGroup = new ThreadGroup("test");
        for (int i = 0; i < threads.length; i++) {
            File path = new File("core/src/test/resources/foto/" + ((i % 5) + 1));
            PhotoAlbumKey albumKey = new PhotoAlbumKey(path);
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

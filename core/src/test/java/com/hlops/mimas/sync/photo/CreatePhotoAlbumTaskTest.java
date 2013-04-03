package com.hlops.mimas.sync.photo;

import com.google.inject.Inject;
import com.hlops.mimas.data.EntityKey;
import com.hlops.mimas.data.key.photo.PhotoAlbumKey;
import com.hlops.mimas.module.ServiceModule;
import com.hlops.mimas.service.QueueService;
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
    private QueueService queue;

    class TestThread extends Thread {
        private EntityKey albumKey;

        TestThread(ThreadGroup threadGroup, String name, EntityKey albumKey) {
            super(threadGroup, name);
            this.albumKey = albumKey;
        }

        @Override
        public void run() {
            CreatePhotoAlbumTask createPhotoAlbumTask = queue.get(albumKey, CreatePhotoAlbumTask.getFactory());
            try {
                System.out.println(createPhotoAlbumTask.get());
            } catch (Exception e) {
                fail(e.getMessage());
            }
        }
    }

    @Test
    public void testGetAlbum() throws Exception {

        File path = new File("core/src/test/resources/foto/2");
        PhotoAlbumKey albumKey = new PhotoAlbumKey(path);

        TestThread[] threads = new TestThread[10];

        ThreadGroup threadGroup = new ThreadGroup("test");
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new TestThread(threadGroup, "thread#" + i, albumKey);
        }

        for (Thread thread : threads) {
            thread.start();
            Thread.sleep(5);
        }
        for (Thread thread : threads) {
            thread.join();
        }
/*
        PhotoAlbum album = CreatePhotoAlbumTask.getAlbum(new PhotoAlbumKey(path));
*/

    }
}

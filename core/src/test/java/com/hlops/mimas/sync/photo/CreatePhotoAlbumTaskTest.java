package com.hlops.mimas.sync.photo;

import com.hlops.mimas.data.bean.photo.PhotoAlbum;
import com.hlops.mimas.data.key.photo.PhotoAlbumKey;
import junit.framework.TestCase;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 01.04.13
 * Time: 23:35
 * To change this template use File | Settings | File Templates.
 */
public class CreatePhotoAlbumTaskTest extends TestCase {

    public void testGetAlbum() throws Exception {

        File path = new File("core/src/test/resources/foto/1");
        PhotoAlbum album = CreatePhotoAlbumTask.getAlbum(new PhotoAlbumKey(path));
    }
}

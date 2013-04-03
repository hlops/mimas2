package com.hlops.mimas.sync.photo;

import com.hlops.mimas.data.EntityKey;
import com.hlops.mimas.data.bean.photo.ImageSize;
import com.hlops.mimas.data.bean.photo.Photo;
import com.hlops.mimas.data.key.photo.PhotoAlbumKey;
import com.hlops.mimas.data.key.photo.PhotoKey;
import com.hlops.mimas.sync.EntityKeyFuture;
import com.hlops.mimas.sync.TaskFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 02.04.13
 * Time: 1:03
 * To change this template use File | Settings | File Templates.
 */
public class CreatePhotoTask extends FutureTask<Photo> implements EntityKeyFuture<Photo, PhotoKey> {

    private final PhotoKey key;

    public CreatePhotoTask(final PhotoKey key) {
        super(new Callable<Photo>() {
            public Photo call() throws Exception {
                return getPhoto(key);
            }
        });
        this.key = key;
    }

    public PhotoKey getKey() {
        return key;
    }

    private static Photo getPhoto(PhotoKey key) throws IOException {
        BufferedImage image = ImageIO.read(key.getFile());
        Photo foto = new Photo(key.getName(), null, new ImageSize(image.getWidth(), image.getHeight()));
        return foto;
    }

    private static TaskFactory<CreatePhotoAlbumTask> taskFactory = new TaskFactory<CreatePhotoAlbumTask>() {
        public CreatePhotoAlbumTask create(EntityKey key) {
            return new CreatePhotoAlbumTask((PhotoAlbumKey) key);
        }
    };

    public static TaskFactory<CreatePhotoAlbumTask> getFactory() {
        return taskFactory;
    }
}

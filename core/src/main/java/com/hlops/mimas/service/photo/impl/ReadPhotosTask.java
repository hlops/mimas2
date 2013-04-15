package com.hlops.mimas.service.photo.impl;

import com.hlops.mimas.config.MimasConfig;
import com.hlops.mimas.data.TaskKey;
import com.hlops.mimas.data.bean.photo.Photo;
import com.hlops.mimas.data.bean.photo.PhotoAlbum;
import com.hlops.mimas.data.key.photo.PhotoAlbumKey;
import com.hlops.mimas.data.key.photo.PhotoKey;
import com.hlops.mimas.service.QueueService;
import com.hlops.mimas.sync.RunnableTask;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 08.04.13
 * Time: 22:56
 * To change this template use File | Settings | File Templates.
 */
public class ReadPhotosTask implements RunnableTask {

    private final QueueService queue;
    private final PhotoAlbumKey key;
    private final PhotoAlbum album;
    private final File configFile;
    private final TaskKey taskKey;

    public ReadPhotosTask(QueueService queue, PhotoAlbumKey key, PhotoAlbum album, File configFile) {
        this.queue = queue;
        this.key = key;
        this.album = album;
        this.configFile = configFile;
        taskKey = new TaskKey(this.getClass(), key);
    }

    @Override
    public TaskKey getKey() {
        return taskKey;
    }

    public void run() {
        File[] files = configFile.getParentFile().listFiles(new FileFilter() {
            public boolean accept(File f) {
                //noinspection SimplifiableIfStatement
                if (wildcardMatches(f, MimasConfig.getInstance().getPhotoConfig().getIncludedWildcard())) {
                    return !wildcardMatches(f, album.getExcludedWildcard());
                }
                return false;
            }
        });
        List<Future> subTasks = new ArrayList<Future>();
        if (files != null) {
            for (File f : files) {
                CreatePhotoTask createPhotoTask = new CreatePhotoTask(new PhotoKey(key, f.getName()));
                subTasks.add(queue.getFuture(createPhotoTask));
            }
        }

        for (Future f : subTasks) {
            try {
                album.getItems().add((Photo) f.get());
                System.out.println(Runtime.getRuntime().freeMemory() / 1000 /1000.);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (ExecutionException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    private static boolean wildcardMatches(File f, @Nullable String wildcards) {
        if (StringUtils.isNotBlank(wildcards)) {
            //noinspection ConstantConditions
            for (String wildcard : wildcards.split("[ ,;]")) {
                if (FilenameUtils.wildcardMatch(f.getName(), wildcard, IOCase.INSENSITIVE)) {
                    return true;
                }
            }
        }
        return false;
    }

}

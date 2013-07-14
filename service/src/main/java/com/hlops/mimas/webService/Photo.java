package com.hlops.mimas.webService;

import com.google.inject.Inject;
import com.hlops.mimas.core.data.bean.photo.PhotoAlbum;
import com.hlops.mimas.core.data.key.photo.PhotoAlbumKey;
import com.hlops.mimas.core.service.photo.PhotoService;
import com.hlops.mimas.logic.photo.PhotoManager;
import com.hlops.mimas.model.photo.PhotosBean;
import com.hlops.mimas.utils.CookieProvider;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 24.06.13
 * Time: 1:14
 * To change this template use File | Settings | File Templates.
 */
@Path("/photos")
public class Photo {

    @Context
    private HttpServletRequest httpRequest;

    @Inject
    private PhotoService photoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PhotosBean list(@QueryParam("query") String query) throws JAXBException, ExecutionException, InterruptedException {
        final PhotoAlbumKey albumKey = new PhotoAlbumKey("D:\\Media\\mimas\\photos\\6");
        final PhotosBean photosBean = new PhotosBean(new CookieProvider(httpRequest));

        PhotoAlbum album = photoService.getAlbum(albumKey);
        photosBean.addAll(album, PhotoManager.getInstance().find(album, query));

        //final LeftMenuOrder order = (LeftMenuOrder) photosBean.getLeftMenuItem("lbPhOA");
        return photosBean;
    }

}

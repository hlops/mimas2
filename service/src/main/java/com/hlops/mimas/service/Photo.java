package com.hlops.mimas.service;

import com.hlops.mimas.model.leftMenu.impl.LeftMenuOrder;
import com.hlops.mimas.model.photo.PhotoItemBean;
import com.hlops.mimas.model.photo.PhotosBean;
import com.hlops.mimas.utils.CookieProvider;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

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
    HttpServletRequest httpRequest;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PhotosBean list(@QueryParam("query") String query) {
        final PhotosBean photosBean = new PhotosBean(new CookieProvider(httpRequest));
        final List<PhotoItemBean> photos = photosBean.getPhotos();

        addFiltered(query, photos, "Project 1", "some text");
        addFiltered(query, photos, "Project 2", "some more text");
        addFiltered(query, photos, "Project 3", "some other text");
        addFiltered(query, photos, "Project 4", "different text");
        addFiltered(query, photos, "Project 5", "very different text");

        final LeftMenuOrder order = (LeftMenuOrder) photosBean.getLeftMenuItem("lbPhOA");
        if (order != null && order.isAsc()) {
            Collections.reverse(photos);
        }
        return photosBean;
    }

    private void addFiltered(String query, List<PhotoItemBean> projects, String name, String description) {
        if (query == null || name.toLowerCase().contains(query.toLowerCase()) || description.toLowerCase().contains(query.toLowerCase())) {
            projects.add(new PhotoItemBean(name, description, ""));
        }
    }
}

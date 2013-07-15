package com.hlops.mimas.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 7/15/13
 * Time: 7:10 PM
 */
public class ResourceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rawResourcePath = request.getPathInfo();
        String root = request.getParameter("root");
    }
}

package com.hlops.mimas.utils;

import org.jetbrains.annotations.Nullable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 7/11/13
 * Time: 8:20 PM
 */
public class CookieProvider {

    private Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();

    public CookieProvider(HttpServletRequest request) {
        final Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
    }

    @Nullable
    public Cookie getCookie(String name) {
        return cookieMap.get(name);
    }

    @Nullable
    public String getCookieValue(String name) {
        final Cookie cookie = getCookie(name);
        return cookie == null ? null : cookie.getValue();
    }
}

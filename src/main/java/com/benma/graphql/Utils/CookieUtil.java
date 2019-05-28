package com.benma.graphql.Utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author:szjz
 * date:2019/5/28
 */
public class CookieUtil {

    /**
     * 设置cookie
     *
     * @param response http响应
     * @param name     cookie键
     * @param value    cookie值
     * @param maxAge   过期时间
     */
    public static void set(HttpServletResponse response,
                           String name,
                           String value,
                           Integer maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 获取cookie
     * @param request http请求
     * @param cookieName cookie名字
     * @return
     */
    public static Cookie get(HttpServletRequest request,
                             String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie;
                }
            }
        }
        return null;
    }

}

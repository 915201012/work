package com.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getRequestURI().endsWith("login")) {
            return true;
        }
        Object username = request.getSession().getAttribute("username");
        if (username != null && username.toString().equals("admin")) {
            return true;
        }
        response.sendRedirect("login.jsp");
        return false;
    }
}

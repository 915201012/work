package com.demo.controller;

import com.demo.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/demo")
public class DemoController {

    private static final String ADMIN = "admin";


    @Autowired
    private ResumeService resumeService;

    @RequestMapping("/test")
    public void test() {
        System.out.println("success");
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (ADMIN.equals(username) && ADMIN.equals(password)) {
            request.getSession().setAttribute("username","admin");
            return "show";
        }
        return "login";
    }

}

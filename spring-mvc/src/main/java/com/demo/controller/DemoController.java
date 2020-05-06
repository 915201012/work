package com.demo.controller;

import com.demo.service.IDemoService;
import com.framework.annotations.Autowired;
import com.framework.annotations.Controller;
import com.framework.annotations.RequestMapping;
import com.framework.annotations.Security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private IDemoService demoService;


    @RequestMapping("/query")
    public String get(HttpServletRequest request, HttpServletResponse response, String name) {

        return demoService.get(name);
    }


    @RequestMapping("/one")
    @Security({"tom", "jerry"})
    public String one(HttpServletResponse resp) throws Exception {
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-Type", "text/html;charset=UTF-8");
        resp.getWriter().write("访问成功 ~~~");
        return "method : one";
    }

    @RequestMapping("/two")
    @Security({"tom"})
    public String two(HttpServletResponse resp) throws Exception {
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-Type", "text/html;charset=UTF-8");
        resp.getWriter().write("访问成功 ~~~");
        return "method : two";
    }

    @RequestMapping("/three")
    @Security({"jerry"})
    public String three(HttpServletResponse resp) throws Exception {
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-Type", "text/html;charset=UTF-8");
        resp.getWriter().write("访问成功 ~~~");
        return "method : three";
    }
}

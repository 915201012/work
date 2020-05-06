package com.demo.servlet;

import com.demo.customanno.Autowired;
import com.demo.customanno.Transactional;
import com.demo.pojo.Result;
import com.demo.service.TransferService;

import com.demo.util.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "transferServlet", urlPatterns = "/transferServlet")
@Transactional
public class TransferServlet extends HttpServlet {

    @Autowired
    private TransferService transferService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String fromCardNo = req.getParameter("fromCardNo");
        String toCardNo = req.getParameter("toCardNo");
        String moneyStr = req.getParameter("money");
        int money = Integer.parseInt(moneyStr);
        Result result = new Result();
        try {
            transferService.transfer(fromCardNo, toCardNo, money);
            result.setStatus("200");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("401");
            result.setMessage(e.toString());
        }

        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().println(JsonUtils.object2Json(result));

    }

}

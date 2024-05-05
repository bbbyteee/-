package com.web.servlet.transport;

import com.google.gson.Gson;
import com.web.bean.Seller;
import com.web.bean.Transport;
import com.web.dao.SellerDao;
import com.web.dao.TransportDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author byte~
 * @version 1.0
 */

//@WebServlet用于配置当前的请求访问的地址
@WebServlet("/Query_transport")
public class QueryTransportServlet extends HttpServlet {

    //接收请求的处理方法
    @Override          //  请求:request     响应:response
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //把响应的编码设置为UTF-8，以及响应的格式设为json
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        //1、获取请求中提交的userid
        String m_id = request.getParameter("m_id");
        // --创建dao的对象，然后调用方法查询所有的生产商
        TransportDao dao = new TransportDao();
        Transport t = dao.Query_M_id(m_id);
        //创建一个HashMap集合对象
        HashMap result = new HashMap();
        //创建一个Gson对象
        Gson gson = new Gson();
        //2、响应-json数据的结果
        if (t!=null) {
            result.put("ok", true);
            result.put("t",t);
        } else {
            result.put("ok", false);
            result.put("message", "该编号不存在");
        }
        response.getWriter().append(gson.toJson(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
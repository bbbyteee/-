package com.web.servlet.transport;

import com.google.gson.Gson;
import com.web.bean.Transport;
import com.web.dao.TransportDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author byte~
 * @version 1.0
 */

//@WebServlet用于配置当前的请求访问的地址
@WebServlet("/transportFuzzyQuery")
public class Fuzzy_QueryTransportServlet extends HttpServlet {

    //接收请求的处理方法
    @Override          //  请求:request     响应:response
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //把响应的编码设置为UTF-8，以及响应的格式设为json
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        //1、获取请求中提交的d_id和d_name   【使用getParameter方法根据key来获取value】
        String t_name = request.getParameter("t_name");
        // --创建dao的对象，然后调用方法查询所有的生产商
        TransportDao dao = new TransportDao();
        List<Transport> Transport_list = dao.Fuzzy_queryTransport(new Transport(t_name,null));
        //创建一个HashMap集合对象
        HashMap result = new HashMap();
        //创建一个Gson对象
        Gson gson = new Gson();
        //2、响应-json数据的结果
        result.put("transport_list", Transport_list);
        response.getWriter().append(gson.toJson(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
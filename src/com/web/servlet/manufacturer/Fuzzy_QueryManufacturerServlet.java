package com.web.servlet.manufacturer;

import com.google.gson.Gson;
import com.web.bean.Manufacturer;
import com.web.dao.ManufacturerDao;

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

//@WebServlet用于配置当前的请求访问的地址  /userLogin
@WebServlet("/manufacturerFuzzyQuery")
public class Fuzzy_QueryManufacturerServlet extends HttpServlet {

    //接收请求的处理方法
    @Override          //  请求:request     响应:response
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //把响应的编码设置为UTF-8，以及响应的格式设为json
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        //1、获取请求中提交的d_id和d_name   【使用getParameter方法根据key来获取value】
        String m_operator = request.getParameter("m_operator");
        // --创建dao的对象，然后调用方法查询所有的生产商
        ManufacturerDao dao = new ManufacturerDao();
        List<Manufacturer> manufacturer_list = dao.Fuzzy_queryManufacturer(new Manufacturer(m_operator));
        //创建一个HashMap集合对象
        HashMap result = new HashMap();
        //创建一个Gson对象
        Gson gson = new Gson();
        //2、响应-json数据的结果
        result.put("manufacturer_list", manufacturer_list);
        response.getWriter().append(gson.toJson(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
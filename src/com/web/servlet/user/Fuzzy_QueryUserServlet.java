package com.web.servlet.user;

import com.google.gson.Gson;
import com.web.bean.User;
import com.web.dao.UserDao;

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
@WebServlet("/userFuzzyQuery")
public class Fuzzy_QueryUserServlet extends HttpServlet {

    //接收请求的处理方法
    @Override          //  请求:request     响应:response
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //把响应的编码设置为UTF-8，以及响应的格式设为json
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        //1、获取请求中提交的d_id和d_name   【使用getParameter方法根据key来获取value】
        String u_id = request.getParameter("u_id");
        String u_nickname = request.getParameter("u_nickname");
        String u_name = request.getParameter("u_name");
        String u_phone = request.getParameter("u_phone");
        // --创建dao的对象，然后调用方法查询所有的部门
        UserDao dao = new UserDao();
        List<User> user_list = dao.Fuzzy_queryUser(new User(u_id,u_nickname,null,u_name,null,0,u_phone,null));
        //创建一个HashMap集合对象
        HashMap result = new HashMap();
        //创建一个Gson对象
        Gson gson = new Gson();
        //2、响应-json数据的结果
        result.put("user_list", user_list);
        response.getWriter().append(gson.toJson(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
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

//@WebServlet用于配置当前的请求访问的地址  /userUpdate
@WebServlet("/userUpdate")
public class UpdateServlet extends HttpServlet {

    //接收请求的处理方法
    @Override          //  请求:request     响应:response
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //把响应的编码设置为UTF-8，以及响应的格式设为json
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        //1、获取请求中提交的username和userpassword   【使用getParameter方法根据key来获取value】
        String u_id = request.getParameter("userid");
        String u_nickname = request.getParameter("usernickname");
        String u_password = request.getParameter("userpassword");
        String u_name = request.getParameter("username");
        String u_sex = request.getParameter("usersex");
        String u_phone = request.getParameter("userphone");
        //创建一个HashMap集合对象
        HashMap result = new HashMap();
        //创建一个Gson对象
        Gson gson = new Gson();
        //非空判断
        if (u_id == null || u_id.equals("")) {
            result.put("message", "用户编号不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (u_nickname == null || u_nickname.equals("")) {
            result.put("message", "用户昵称不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (u_password == null || u_password.equals("")) {
            result.put("message", "用户密码不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (u_name == null || u_name.equals("")) {
            result.put("message", "用户姓名不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (u_phone == null || u_phone.equals("")) {
            result.put("message", "用户电话号码不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        //构建User 对象
        User user = new User(u_id, u_nickname, u_password, u_name, u_sex, 0, u_phone, null);
        // 2、创建dao 的对象，然后调佣dao方法
        UserDao dao = new UserDao();
        boolean ok = dao.updateUser(user);
        // 3、判断是否修改成功
        if (ok) {
            result.put("ok", true);
        } else {
            result.put("ok", false);
            result.put("message", "用户修改失败");
        }
        //3、响应 --写出结果
        response.getWriter().append(gson.toJson(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

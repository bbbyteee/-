//该模块用于实现更改密码操作的Servlet层连接

package com.web.servlet;

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

/**
 * @author byte~
 * @version 1.0
 */

//@WebServlet用于配置当前的请求访问的地址  /changePassword
@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {

    //接收请求的处理方法
    @Override          //  请求:request     响应:response
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //把响应的编码设置为UTF-8，以及响应的格式设为json
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        //1、获取请求中提交的username和userpassword   【使用getParameter方法根据key来获取value】
        String u_id = request.getParameter("userid");
        String u_password = request.getParameter("userpassword");
        String u_newpassword = request.getParameter("usernewpassword");
        String u_newpassword1 = request.getParameter("usernewpassword1");
        //创建dao的对象，然后调用方法查询
        UserDao dao = new UserDao();
        User user = dao.queryUserLogin(u_id, u_password);
        //创建一个HashMap集合对象
        HashMap result = new HashMap();
        //创建一个Gson对象
        Gson gson = new Gson();
        //非空判断
        if (u_password == null || u_password.equals("")) {
            result.put("message", "用户旧密码不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (u_newpassword == null || u_newpassword.equals("")) {
            result.put("message", "用户新密码不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (u_newpassword1 == null || u_newpassword1.equals("")) {
            result.put("message", "用户确认密码不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if(!u_newpassword1.equals(u_newpassword)){
            result.put("message", "用户新密码与确认密码不相同");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        //2、判断是否旧密码正确  【admin-123】
        if (user!=null) {
            //3、响应 --登旧密码正确 --
            // 响应-json数据的结果
            if(user.getU_password().equals(u_newpassword)){
                result.put("ok", false);
                result.put("message", "新旧密码不能相同！");
            }else{
                boolean ok = dao.changePassowrd(u_id,u_newpassword);
                if(ok){
                    result.put("ok", true);
                }
            }
        } else {
            //3、响应 --登录失败  -- 在页面中直接写出信息
            // 响应-json数据的结果
            result.put("ok", false);
            result.put("message", "旧密码错误！");
        }
        //3、响应 --写出结果
        response.getWriter().append(gson.toJson(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

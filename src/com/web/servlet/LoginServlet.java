//该模块用于实现登录操作的Servlet层连接

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

//@WebServlet用于配置当前的请求访问的地址  /userLogin
@WebServlet("/userLogin")
public class LoginServlet extends HttpServlet {

    //接收请求的处理方法
    @Override          //  请求:request     响应:response
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //把响应的编码设置为UTF-8，以及响应的格式设为json
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        //1、获取请求中提交的username和userpassword
        String userid = request.getParameter("userid");
        String userpassword = request.getParameter("userpassword");
        String code = request.getParameter("verifycode");
        String check_card = (String) request.getSession().getAttribute("check_card");
        //创建dao的对象，然后调用方法查询
        UserDao dao = new UserDao();
        User user = dao.queryUserLogin(userid, userpassword);
        //创建一个HashMap对象
        HashMap result = new HashMap();
        //创建一个Gson对象
        Gson gson = new Gson();
        //2、判断是否登录成功
        if (code.equalsIgnoreCase(check_card)) {
            if (user!=null) {
                //3、响应 --登录成功 -- 在页面中直接写出信息
                // 响应-json数据的结果
                result.put("ok", true);
                result.put("user", user);

            } else {
                //3、响应 --登录失败  -- 在页面中直接写出信息
                // 响应-json数据的结果
                result.put("ok", false);
                result.put("message", "用户名或密码错误！");
            }
        }else{
            result.put("ok", false);
            result.put("message", "验证码错误！");
        }

        // 把集合数据转换为json字符串，然后通过响应写出
        String re_json = gson.toJson(result);
        response.getWriter().append(re_json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}


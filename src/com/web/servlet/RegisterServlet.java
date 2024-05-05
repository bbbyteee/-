//该模块用于实现注册操作的Servlet层连接

package com.web.servlet;

import com.google.gson.Gson;
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

@WebServlet("/userRegister")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //把响应的编码设置为UTF-8，以及响应的格式设为json
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        //1. 获取用户名和密码数据
        String id = request.getParameter("u_id");
        String nickname = request.getParameter("u_nickname");
        String password = request.getParameter("u_password");
        String password1 = request.getParameter("u_password1");
        String name = request.getParameter("u_name");
        String sex = request.getParameter("sex");
        String position = request.getParameter("position");
        String phone = request.getParameter("u_phone");
        int p = Integer.parseInt(position);
        //创建一个HashMap对象
        HashMap result = new HashMap();
        //创建一个Gson对象
        Gson gson = new Gson();
        //如果部分信息为空
        if(!(id != null && name!=null && password!=null && password1!=null &&
                nickname!=null && phone!=null)) {
            //3、响应 --登录失败  -- 在页面中直接写出信息
            // 响应-json数据的结果
            result.put("ok", false);
            result.put("message", "部分信息为空！");
            // 把集合数据转换为json字符串，然后通过响应写出
            String re_json = gson.toJson(result);
            response.getWriter().append(re_json);
            return;
        }
        //3. 判断注册成功与否（判断密码是否相同）
        //注册功能，跳转登陆页面
        if (password.equals(password1)&&password!=null&&password1!=null) {
            //添加到数据库
            UserDao dao = new UserDao();
            boolean ok = dao.queryRegister(id,nickname,password,name,sex,p,phone);
            if (ok) {
                //3、响应 --注册成功 -- 在页面中直接写出信息
                // 响应-json数据的结果
                result.put("ok", true);
                result.put("username", name);
            } else {
                //3、响应 --注册失败  -- 在页面中直接写出信息
                // 响应-json数据的结果
                result.put("ok", false);
                result.put("message", "用户id已存在！");
            }
        } else {
            //3、响应 --登录失败  -- 在页面中直接写出信息
            // 响应-json数据的结果
            result.put("ok", false);
            result.put("message", "密码不一致或为空！");
        }
        // 把集合数据转换为json字符串，然后通过响应写出
        String re_json = gson.toJson(result);
        response.getWriter().append(re_json);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

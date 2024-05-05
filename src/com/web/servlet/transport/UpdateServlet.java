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

/**
 * @author byte~
 * @version 1.0
 */

//@WebServlet用于配置当前的请求访问的地址  /userUpdate
@WebServlet("/transportUpdate")
public class UpdateServlet extends HttpServlet {

    //接收请求的处理方法
    @Override          //  请求:request     响应:response
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //把响应的编码设置为UTF-8，以及响应的格式设为json
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        //1、获取请求中提交的username和userpassword   【使用getParameter方法根据key来获取value】
        String u_id = request.getParameter("u_id");
        String t_id = request.getParameter("t_id");
        String t_name = request.getParameter("t_name");
        String t_operator = request.getParameter("t_operator");
        String t_phone = request.getParameter("t_phone");
        String t_address = request.getParameter("t_address");
        String t_in_date = request.getParameter("t_in_date");
        String t_out_date = request.getParameter("t_out_date");
        String t_type_transportation = request.getParameter("t_type_transportation");
        String t_destination = request.getParameter("t_destination");
        String m_id = request.getParameter("m_id");
        //创建一个HashMap集合对象
        HashMap result = new HashMap();
        //创建一个Gson对象
        Gson gson = new Gson();
        //非空判断
        if (t_name == null || t_name.equals("")) {
            result.put("message", "单位名称不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (t_operator == null || t_operator.equals("")) {
            result.put("message", "负责人不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (t_phone == null || t_phone.equals("")) {
            result.put("message", "联系电话不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (t_address == null || t_address.equals("")) {
            result.put("message", "地址不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (t_in_date == null || t_in_date.equals("")) {
            result.put("message", "入库日期不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (t_out_date == null || t_out_date.equals("")) {
            result.put("message", "出库日期不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (t_type_transportation == null || t_type_transportation.equals("")) {
            result.put("message", "运输方式不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (t_destination == null || t_destination.equals("")) {
            result.put("message", "主要流向不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (m_id == null || m_id.equals("")) {
            result.put("message", "食品id不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        //构建Transport 对象
        Transport t = new Transport(t_id, null, t_name, t_operator,
                t_phone, t_address, t_in_date, t_out_date,
                t_type_transportation, t_destination, u_id, m_id);
        // 2、创建dao 的对象，然后调佣dao方法
        TransportDao dao = new TransportDao();
        boolean ok = dao.updateTransport(t);
        // 3、判断是否修改成功
        if (ok) {
            result.put("ok", true);
        } else {
            result.put("ok", false);
            result.put("message", "信息修改失败");
        }
        //3、响应 --写出结果
        response.getWriter().append(gson.toJson(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

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

/**
 * @author byte~
 * @version 1.0
 */

//@WebServlet用于配置当前的请求访问的地址  /userUpdate
@WebServlet("/manufacturerUpdate")
public class UpdateServlet extends HttpServlet {

    //接收请求的处理方法
    @Override          //  请求:request     响应:response
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //把响应的编码设置为UTF-8，以及响应的格式设为json
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        //1、获取请求中提交的username和userpassword   【使用getParameter方法根据key来获取value】
        String u_id = request.getParameter("u_id");
        String m_id = request.getParameter("m_id");
        String m_operator = request.getParameter("m_operator");
        String m_phone = request.getParameter("m_phone");
        String m_mail = request.getParameter("m_mail");
        String m_foodname = request.getParameter("m_foodname");
        String m_origin_place = request.getParameter("m_origin_place");
        String m_date_manufacture = request.getParameter("m_date_manufacture");
        String m_in_date = request.getParameter("m_in_date");
        String m_packaging_date = request.getParameter("m_packaging_date");
        String m_out_date = request.getParameter("m_out_date");
        String m_weather = request.getParameter("m_weather");
        //创建一个HashMap集合对象
        HashMap result = new HashMap();
        //创建一个Gson对象
        Gson gson = new Gson();
        //非空判断
        if (m_operator == null || m_operator.equals("")) {
            result.put("message", "经办人不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (m_phone == null || m_phone.equals("")) {
            result.put("message", "电话号码不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (m_mail == null || m_mail.equals("")) {
            result.put("message", "邮箱不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (m_foodname == null || m_foodname.equals("")) {
            result.put("message", "食品名称不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (m_origin_place == null || m_origin_place.equals("")) {
            result.put("message", "产地不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (m_date_manufacture == null || m_date_manufacture.equals("")) {
            result.put("message", "生产日期不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (m_in_date == null || m_in_date.equals("")) {
            result.put("message", "入库日期不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (m_packaging_date == null || m_packaging_date.equals("")) {
            result.put("message", "打包日期不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (m_out_date == null || m_out_date.equals("")) {
            result.put("message", "出库日期不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (m_weather == null || m_weather.equals("")) {
            result.put("message", "气候不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        //构建Manufacturer 对象
        Manufacturer m = new Manufacturer(m_id,null,m_operator,
                m_phone,m_mail,m_foodname,m_origin_place,
                m_date_manufacture,m_in_date,m_packaging_date,
                m_out_date,m_weather,u_id);
        // 2、创建dao 的对象，然后调佣dao方法
        ManufacturerDao dao = new ManufacturerDao();
        boolean ok = dao.updateManufacturer(m);
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

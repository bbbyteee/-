
package com.web.servlet.manufacturer;

import com.google.gson.Gson;
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
//@WebServlet用于配置当前的请求访问的地址
@WebServlet("/manufacturerDelete")
public class DeleteServlet extends HttpServlet {

    //接收请求的处理方法
    @Override          //  请求:request     响应:response
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //把响应的编码设置为UTF-8，以及响应的格式设为json
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        //1、获取请求中提交的userid
        String m_id = request.getParameter("m_id");
        //创建dao的对象，然后调用方法查询
        ManufacturerDao dao = new ManufacturerDao();
        boolean ok = dao.deleteManufacturer(m_id);
        //创建一个HashMap对象
        HashMap result = new HashMap();
        //创建一个Gson对象
        Gson gson = new Gson();
        //2、判断是否删除成功
        if (ok) {
            //3、响应 --删除成功 -- 在页面中直接写出信息
            // 响应-json数据的结果
            result.put("ok", true);
            result.put("m_id", m_id);

        } else {
            //3、响应 --删除失败  -- 在页面中直接写出信息
            // 响应-json数据的结果
            result.put("ok", false);
            result.put("message", "不存在该id！");
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

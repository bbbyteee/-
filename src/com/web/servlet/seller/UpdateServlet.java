package com.web.servlet.seller;

import com.google.gson.Gson;
import com.web.bean.Seller;
import com.web.dao.SellerDao;

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
@WebServlet("/sellerUpdate")
public class UpdateServlet extends HttpServlet {

    //接收请求的处理方法
    @Override          //  请求:request     响应:response
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //把响应的编码设置为UTF-8，以及响应的格式设为json
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        //1、获取请求中提交的username和userpassword   【使用getParameter方法根据key来获取value】
        String u_id = request.getParameter("u_id");
        String s_id = request.getParameter("s_id");
        String s_seller = request.getParameter("s_seller");
        String s_COVID19_Report = request.getParameter("s_COVID19_Report");
        String s_in_date = request.getParameter("s_in_date");
        String s_out_date = request.getParameter("s_out_date");
        String s_launch_date = request.getParameter("s_launch_date");
        String m_id = request.getParameter("m_id");
        //创建一个HashMap集合对象
        HashMap result = new HashMap();
        //创建一个Gson对象
        Gson gson = new Gson();
        //非空判断
        if (s_seller == null || s_seller.equals("")) {
            result.put("message", "经销商不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (s_COVID19_Report == null || s_COVID19_Report.equals("")) {
            result.put("message", "新冠检测结果不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (s_in_date == null || s_in_date.equals("")) {
            result.put("message", "入库日期不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (s_out_date == null || s_out_date.equals("")) {
            result.put("message", "出库日期不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (s_launch_date == null || s_launch_date.equals("")) {
            result.put("message", "上架日期不能为空");
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
        //构建Seller 对象
        Seller s = new Seller(s_id, null, s_seller,
                s_COVID19_Report,s_in_date,s_out_date,s_launch_date, u_id, m_id);
        // 2、创建dao 的对象，然后调佣dao方法
        SellerDao dao = new SellerDao();
        boolean ok = dao.updateSeller(s);
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

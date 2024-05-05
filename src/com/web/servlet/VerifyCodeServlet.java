package com.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author byte~
 * @version 1.0
 */

@WebServlet( "/VerifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置宽高
        int width = 100;
        int height = 50;
        //创建对象，在内存验证码图
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        //美化图片
        Graphics graphics = image.getGraphics();
        //画边框
        graphics.setColor(Color.magenta);
        graphics.drawRect(0,0,width-1,height-1);
        //填充背景颜色
        graphics.setColor(Color.cyan);//画笔颜色
        graphics.drawRect(0,0,width,height);
        //定义随机抽取池
        String str = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuioplkjhgfdsazxcvbnm0123456789";
        //生成随机角标
        Random ran = new Random();
        //接收验证码
        StringBuffer sb = new StringBuffer();
        //写入验证码
        for (int i = 1; i <= 4; i++) {
            int index = ran.nextInt(str.length());
            //获取随机字符
            char c = str.charAt(index);
            sb.append(c);
            graphics.setFont(new Font("Tahoma", Font.BOLD, 18));
            graphics.drawString(c+"",width/5*i,height/2);
        }
        //存入session
        String session_check = sb.toString();
        request.getSession().setAttribute("check_card",session_check);
        //画干扰线
        graphics.setColor(Color.green);
        for (int i = 0; i < 10; i++) {
            int x1 = ran.nextInt(width);
            int x2 = ran.nextInt(width);
            int y1 = ran.nextInt(height);
            int y2 = ran.nextInt(height);
            graphics.drawLine(x1,y1,x2,y2);
        }
        //将图片画到页面
        ImageIO.write(image,"jpg",response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}


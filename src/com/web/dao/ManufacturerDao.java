//该模块用于实现生产商相应操作

package com.web.dao;

import com.web.bean.Manufacturer;
import com.web.util.DataSources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author byte~
 * @version 1.0
 */

public class ManufacturerDao {

    //定义一个方法查询该m_id是否存在并返回该条信息
    public Manufacturer Query_M_id(String id) {
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
            return null;
        }
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        // 2、定义查询的sql语句
        String sql = "select * from manufacturer where m_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, id);
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、得到结果
            if (result.next()) {
                //判断结果集中是否有下一条记录//得到一行的数据
                String m_id = result.getString("m_id");
                String m_create_date = result.getString("m_create_date");
                String m_operator = result.getString("m_operator");
                String m_phone = result.getString("m_phone");
                String m_mail = result.getString("m_mail");
                String m_foodname = result.getString("m_foodname");
                String m_origin_place = result.getString("m_origin_place");
                String m_date_manufacturer = result.getString("m_date_manufacture");
                String m_in_date = result.getString("m_in_date");
                String m_packaging_date = result.getString("m_packaging_date");
                String m_out_date = result.getString("m_out_date");
                String m_weather = result.getString("m_weather");
                String u_id = result.getString("u_id");
                //将数据封装到Java对象中
                Manufacturer m = new Manufacturer(m_id, m_create_date, m_operator, m_phone, m_mail,
                        m_foodname, m_origin_place, m_date_manufacturer, m_in_date, m_packaging_date,
                        m_out_date, m_weather, u_id);
                DataSources.closeConnection(connection);
                return m;
            } else {
                System.out.println("查无此人" +
                        "m_id");
                DataSources.closeConnection(connection);
                return null;
            }
        } catch (SQLException throwables) {
            DataSources.closeConnection(connection);
            throwables.printStackTrace();
        }
        return null;
    }
    //定义一个方法来查询最新的生产商m_id
    public String Query_Last_M_id(){
        //创建一个id用于存放数据
        String id = "";
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        // 2、定义模糊查询的sql语句
        String sql = "SELECT * FROM manufacturer ORDER BY m_id DESC LIMIT 1;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、循环从result得到结果
            while (result.next()) {//判断结果集中是否有下一条记录
                //得到一行的数据
                String m_id = result.getString("m_id");
                //将数据封装到Java对象中
                id = m_id;
            }
            DataSources.closeConnection(connection);
        } catch (SQLException throwables) {
            DataSources.closeConnection(connection);
            throwables.printStackTrace();
        }
        //返回
        return id;
    }
    //定义一个方法来实现条件查询生产商信息
    public List<Manufacturer> Fuzzy_queryManufacturer(Manufacturer manufacturer){
        //创建一个集合用于存放数据
        List<Manufacturer> list = new ArrayList<>();
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        // 2、定义模糊查询的sql语句
        String sql = "select * from manufacturer where m_operator like ? " +
                " order by m_id+0 asc;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, "%"+manufacturer.getM_operator()+"%");
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、循环从result得到结果
            while (result.next()) {//判断结果集中是否有下一条记录
                //得到一行的数据
                String m_id = result.getString("m_id");
                String m_create_date = result.getString("m_create_date");
                String m_operator = result.getString("m_operator");
                String m_phone = result.getString("m_phone");
                String m_mail = result.getString("m_mail");
                String m_foodname = result.getString("m_foodname");
                String m_origin_place = result.getString("m_origin_place");
                String m_date_manufacturer = result.getString("m_date_manufacture");
                String m_in_date = result.getString("m_in_date");
                String m_packaging_date = result.getString("m_packaging_date");
                String m_out_date = result.getString("m_out_date");
                String m_weather = result.getString("m_weather");
                String u_id = result.getString("u_id");
                //将数据封装到Java对象中
                Manufacturer m = new Manufacturer(m_id, m_create_date, m_operator, m_phone, m_mail,
                        m_foodname, m_origin_place, m_date_manufacturer, m_in_date, m_packaging_date,
                        m_out_date, m_weather, u_id);
                //将对象存放到list中
                list.add(m);
            }
            DataSources.closeConnection(connection);
        } catch (SQLException throwables) {
            DataSources.closeConnection(connection);
            throwables.printStackTrace();
        }
        //返回集合
        return list;
    }
    //定义一个方法来新增生产商信息
    public boolean insertManufacturer(Manufacturer manufacturer) {
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        Calendar calendar = Calendar.getInstance(); // get current instance of the calendar
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String create_time = formatter.format(calendar.getTime());
        System.out.println("添加");
        System.out.println(create_time);
        // 2、定义查询的sql语句
        String sql = "INSERT INTO `manufacturer` VALUES \n" +
                "(null,'"+create_time+"',?,?,?,?,?,?,?,?,?,?,?);";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, manufacturer.getM_operator());
            ps.setString(2, manufacturer.getM_phone());
            ps.setString(3, manufacturer.getM_mail());
            ps.setString(4, manufacturer.getM_foodname());
            ps.setString(5, manufacturer.getM_origin_place());
            ps.setString(6, manufacturer.getM_date_manufacture());
            ps.setString(7, manufacturer.getM_in_date());
            ps.setString(8, manufacturer.getM_packaging_date());
            ps.setString(9, manufacturer.getM_out_date());
            ps.setString(10, manufacturer.getM_weather());
            ps.setString(11,manufacturer.getU_id());
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            // 查询操作使用executeQuery  更新操作使用executeUpdate
            int result = ps.executeUpdate();//执行更新
            DataSources.closeConnection(connection);
            // 5、得到影响行数
            if (result > 0) {//判断影响行数是否大于0
                System.out.println("操作成功");
                return true;
            } else {
                System.out.println("操作失败");
                return false;
            }
        } catch (SQLException throwables) {
            DataSources.closeConnection(connection);
            throwables.printStackTrace();
        }
        return false;
    }
    //定义一个方法来更新生产商信息
    public boolean updateManufacturer(Manufacturer manufacturer) {
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        System.out.println("更新");
        // 2、定义查询的sql语句
        String sql = "update manufacturer set m_operator=?,m_phone=?,m_mail=?,m_foodname=?," +
                "m_origin_place=?,m_date_manufacture=?,m_in_date=?,m_packaging_date=?," +
                "m_out_date=?,m_weather=? where m_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, manufacturer.getM_operator());
            ps.setString(2, manufacturer.getM_phone());
            ps.setString(3, manufacturer.getM_mail());
            ps.setString(4, manufacturer.getM_foodname());
            ps.setString(5, manufacturer.getM_origin_place());
            ps.setString(6, manufacturer.getM_date_manufacture());
            ps.setString(7, manufacturer.getM_in_date());
            ps.setString(8, manufacturer.getM_packaging_date());
            ps.setString(9, manufacturer.getM_out_date());
            ps.setString(10, manufacturer.getM_weather());
            ps.setString(11,manufacturer.getM_id());
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            // 查询操作使用executeQuery  更新操作使用executeUpdate
            int result = ps.executeUpdate();//执行更新
            DataSources.closeConnection(connection);
            // 5、得到影响行数
            if (result > 0) {//判断影响行数是否大于0
                System.out.println("操作成功");
                return true;
            } else {
                System.out.println("操作失败");
                return false;
            }
        } catch (SQLException throwables) {
            DataSources.closeConnection(connection);
            throwables.printStackTrace();
        }
        return false;
    }
    //定义一个方法来实现删除操作
    public boolean deleteManufacturer(String m_id){
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        // 2、定义查询的sql语句
        String sql = "select * from manufacturer where m_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, m_id);
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、得到结果
            if (result.next()) {//判断结果集中是否有下一条记录
                //执行删除语句
                String sql1 = "Delete from manufacturer where m_id=?;";
                ps = connection.prepareStatement(sql1);
                // 给sql语句赋值
                ps.setString(1, m_id);
                // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
                ps.executeUpdate();//执行删除
                DataSources.closeConnection(connection);
                return true;
            } else {
                System.out.println("查无此人");
                DataSources.closeConnection(connection);
                return false;
            }
        } catch (SQLException throwables) {
            DataSources.closeConnection(connection);
            throwables.printStackTrace();
        }
        return false;
    }
}

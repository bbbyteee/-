//该模块用于实现运输商相应操作

package com.web.dao;

import com.web.bean.Transport;
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

public class TransportDao {

    //定义一个方法查询该m_id是否存在
    public Transport Query_M_id(String id) {// 1、获取到数据库链接
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return null;
        }
        Connection connection = DataSources.getConnection();
        // 2、定义查询的sql语句
        String sql = "select * from transport where m_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, id);
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、得到结果
            if (result.next()) {//判断结果集中是否有下一条记录
                // 得到一行的数据
                String t_id = result.getString("t_id");
                String t_create_date = result.getString("t_create_date");
                String t_name = result.getString("t_name");
                String t_operator = result.getString("t_operator");
                String t_phone = result.getString("t_phone");
                String t_address = result.getString("t_address");
                String t_in_date = result.getString("t_in_date");
                String t_out_date = result.getString("t_out_date");
                String t_type_transportation = result.getString("t_type_transportation");
                String t_destination = result.getString("t_destination");
                String u_id = result.getString("u_id");
                String m_id = result.getString("m_id");
                //将数据封装到Java对象中
                Transport t = new Transport(t_id, t_create_date, t_name, t_operator, t_phone, t_address, t_in_date, t_out_date, t_type_transportation, t_destination, u_id, m_id);
                //将对象存放到list中
                DataSources.closeConnection(connection);
                return t;
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
    //定义一个方法来实现条件查询运输商信息
    public List<Transport> Fuzzy_queryTransport(Transport transport){
        //创建一个集合用于存放数据
        List<Transport> list = new ArrayList<>();
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        // 2、定义模糊查询的sql语句
        String sql = "select * from Transport where t_name like ? " +
                " order by t_id+0 asc;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, "%"+transport.getT_name()+"%");
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、循环从result得到结果
            while (result.next()) {//判断结果集中是否有下一条记录
                //得到一行的数据
                String t_id = result.getString("t_id");
                String t_create_date = result.getString("t_create_date");
                String t_name = result.getString("t_name");
                String t_operator = result.getString("t_operator");
                String t_phone = result.getString("t_phone");
                String t_address = result.getString("t_address");
                String t_in_date = result.getString("t_in_date");
                String t_out_date = result.getString("t_out_date");
                String t_type_transportation = result.getString("t_type_transportation");
                String t_destination = result.getString("t_destination");
                String u_id = result.getString("u_id");
                String m_id = result.getString("m_id");
                //将数据封装到Java对象中
                Transport t = new Transport(t_id, t_create_date, t_name, t_operator, t_phone, t_address, t_in_date, t_out_date, t_type_transportation, t_destination, u_id, m_id);
                //将对象存放到list中
                list.add(t);
            }
            DataSources.closeConnection(connection);
        } catch (SQLException throwables) {
            DataSources.closeConnection(connection);
            throwables.printStackTrace();
        }
        //返回集合
        return list;
    }
    //定义一个方法来实现查询运输商信息
    public List<Transport> queryTransport(Transport transport){
        //创建一个集合用于存放数据
        List<Transport> list = new ArrayList<>();
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        // 2、定义模糊查询的sql语句
        String sql = "select * from Transport where t_id = ? " +
                " order by t_id+0 asc;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, transport.getT_id());
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、循环从result得到结果
            while (result.next()) {//判断结果集中是否有下一条记录
                //得到一行的数据
                String t_id = result.getString("t_id");
                String t_create_date = result.getString("t_create_date");
                String t_name = result.getString("t_name");
                String t_operator = result.getString("t_operator");
                String t_phone = result.getString("t_phone");
                String t_address = result.getString("t_address");
                String t_in_date = result.getString("t_in_date");
                String t_out_date = result.getString("t_out_date");
                String t_type_transportation = result.getString("t_type_transportation");
                String t_destination = result.getString("t_destination");
                String u_id = result.getString("u_id");
                String m_id = result.getString("m_id");
                //将数据封装到Java对象中
                Transport t = new Transport(t_id, t_create_date, t_name, t_operator, t_phone, t_address, t_in_date, t_out_date, t_type_transportation, t_destination, u_id, m_id);
                //将对象存放到list中
                list.add(t);
            }
            DataSources.closeConnection(connection);
        } catch (SQLException throwables) {
            DataSources.closeConnection(connection);
            throwables.printStackTrace();
        }
        //返回集合
        return list;
    }
    //定义一个方法来新增运输商信息
    public boolean insertTransport(Transport transport) {
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        Calendar calendar = Calendar.getInstance(); // get current instance of the calendar
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String create_time = formatter.format(calendar.getTime());
        System.out.println("添加");
        System.out.println(create_time);
        // 2、定义查询的sql语句
        String sql = "INSERT INTO `Transport` VALUES \n" +
                "(null,'"+create_time+"',?,?,?,?,?,?,?,?,?,?);";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, transport.getT_name());
            ps.setString(2, transport.getT_operator());
            ps.setString(3, transport.getT_phone());
            ps.setString(4, transport.getT_address());
            ps.setString(5, transport.getT_in_date());
            ps.setString(6, transport.getT_out_date());
            ps.setString(7, transport.getT_type_transportation());
            ps.setString(8, transport.getT_destination());
            ps.setString(9, transport.getU_id());
            ps.setString(10, transport.getM_id());
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
    //定义一个方法来更新运输商信息
    public boolean updateTransport(Transport transport) {
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        System.out.println("更新");
        // 2、定义查询的sql语句
        String sql = "update transport set t_name=?,t_operator=?,t_phone=?,t_address=?," +
                "t_in_date=?,t_out_date=?,t_type_transportation=?," +
                "t_destination=? where t_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, transport.getT_name());
            ps.setString(2, transport.getT_operator());
            ps.setString(3, transport.getT_phone());
            ps.setString(4, transport.getT_address());
            ps.setString(5, transport.getT_in_date());
            ps.setString(6, transport.getT_out_date());
            ps.setString(7, transport.getT_type_transportation());
            ps.setString(8, transport.getT_destination());
            ps.setString(9, transport.getT_id());
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
    public boolean deleteTransport(String t_id){
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        // 2、定义查询的sql语句
        // select * from manager where m_id='10001' and m_password='123456';
        String sql = "select * from Transport where t_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, t_id);
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、得到结果
            if (result.next()) {//判断结果集中是否有下一条记录
                //执行删除语句
                String sql1 = "Delete from Transport where t_id=?;";
                ps = connection.prepareStatement(sql1);
                // 给sql语句赋值
                ps.setString(1, t_id);
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

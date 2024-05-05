//该模块用于实现销售商相应操作

package com.web.dao;

import com.web.bean.Seller;
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

public class SellerDao {

    //定义一个方法查询该m_id是否存在并返回该条信息
    public Seller Query_M_id(String id) {// 1、获取到数据库链接
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return null;
        }
        Connection connection = DataSources.getConnection();
        // 2、定义查询的sql语句
        String sql = "select * from Seller where m_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, id);
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、得到结果
            if (result.next()) {//判断结果集中是否有下一条记录//得到一行的数据
                //得到一行的数据
                String s_id = result.getString("s_id");
                String s_create_date = result.getString("s_create_date");
                String s_seller = result.getString("s_seller");
                String s_COVID19_Report = result.getString("s_COVID19_Report");
                String s_in_date = result.getString("s_in_date");
                String s_out_date = result.getString("s_out_date");
                String s_launch_date = result.getString("s_launch_date");
                String u_id = result.getString("u_id");
                String m_id = result.getString("m_id");
                //将数据封装到Java对象中
                Seller s = new Seller(s_id, s_create_date, s_seller, s_COVID19_Report, s_in_date, s_out_date, s_launch_date, u_id, m_id);
                //将对象存放到list中
                DataSources.closeConnection(connection);
                return s;
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
    //定义一个方法来实现条件查询生产商信息
    public List<Seller> Fuzzy_querySeller(Seller seller){
        //创建一个集合用于存放数据
        List<Seller> list = new ArrayList<>();
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        // 2、定义模糊查询的sql语句
        String sql = "select * from Seller where s_seller like ? " +
                " order by s_id+0 asc;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, "%"+seller.gets_seller()+"%");
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、循环从result得到结果
            while (result.next()) {//判断结果集中是否有下一条记录
                //得到一行的数据
                String s_id = result.getString("s_id");
                String s_create_date = result.getString("s_create_date");
                String s_seller = result.getString("s_seller");
                String s_COVID19_Report = result.getString("s_COVID19_Report");
                String s_in_date = result.getString("s_in_date");
                String s_out_date = result.getString("s_out_date");
                String s_launch_date = result.getString("s_launch_date");
                String u_id = result.getString("u_id");
                String m_id = result.getString("m_id");
                //将数据封装到Java对象中
                Seller s = new Seller(s_id, s_create_date, s_seller, s_COVID19_Report, s_in_date, s_out_date, s_launch_date, u_id, m_id);
                //将对象存放到list中
                list.add(s);
            }
            DataSources.closeConnection(connection);
        } catch (SQLException throwables) {
            DataSources.closeConnection(connection);
            throwables.printStackTrace();
        }
        //返回集合
        return list;
    }
    //定义一个方法来实现查询销售商信息
    public List<Seller> querySeller(Seller seller){
        //创建一个集合用于存放数据
        List<Seller> list = new ArrayList<>();
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        // 2、定义模糊查询的sql语句
        String sql = "select * from Seller where s_id = ? " +
                " order by s_id+0 asc;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, seller.getS_id());
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、循环从result得到结果
            while (result.next()) {//判断结果集中是否有下一条记录
                //得到一行的数据
                String s_id = result.getString("s_id");
                String s_create_date = result.getString("s_create_date");
                String s_seller = result.getString("s_seller");
                String s_COVID19_Report = result.getString("s_COVID19_Report");
                String s_in_date = result.getString("s_in_date");
                String s_out_date = result.getString("s_out_date");
                String s_launch_date = result.getString("s_launch_date");
                String u_id = result.getString("u_id");
                String m_id = result.getString("m_id");
                //将数据封装到Java对象中
                Seller s = new Seller(s_id, s_create_date, s_seller, s_COVID19_Report, s_in_date, s_out_date, s_launch_date, u_id, m_id);
                //将对象存放到list中
                list.add(s);
            }
            DataSources.closeConnection(connection);
        } catch (SQLException throwables) {
            DataSources.closeConnection(connection);
            throwables.printStackTrace();
        }
        //返回集合
        return list;
    }
    //定义一个方法来新增销售商信息
    public boolean insertSeller(Seller seller) {
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        Calendar calendar = Calendar.getInstance(); // get current instance of the calendar
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String create_time = formatter.format(calendar.getTime());
        System.out.println("添加");
        System.out.println(create_time);
        // 2、定义查询的sql语句
        String sql = "INSERT INTO `Seller` VALUES \n" +
                "(null,'"+create_time+"',?,?,?,?,?,?,?);";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, seller.gets_seller());
            ps.setString(2, seller.getS_COVID19_Report());
            ps.setString(3, seller.getS_in_date());
            ps.setString(4, seller.getS_out_date());
            ps.setString(5, seller.getS_launch_date());
            ps.setString(6, seller.getU_id());
            ps.setString(7, seller.getM_id());
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
    //定义一个方法来更新销售商信息
    public boolean updateSeller(Seller seller) {
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        System.out.println("更新");
        // 2、定义查询的sql语句
        String sql = "update seller set s_seller=?,s_COVID19_Report=?," +
                "s_in_date=?,s_out_date=?,s_launch_date=? where s_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, seller.gets_seller());
            ps.setString(2, seller.getS_COVID19_Report());
            ps.setString(3, seller.getS_in_date());
            ps.setString(4, seller.getS_out_date());
            ps.setString(5, seller.getS_launch_date());
            ps.setString(6, seller.getS_id());
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
    public boolean deleteSeller(String s_id){
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        // 2、定义查询的sql语句
        String sql = "select * from Seller where s_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, s_id);
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、得到结果
            if (result.next()) {//判断结果集中是否有下一条记录
                //执行删除语句
                String sql1 = "Delete from Seller where s_id=?;";
                ps = connection.prepareStatement(sql1);
                // 给sql语句赋值
                ps.setString(1, s_id);
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

//该模块用于实现数据库的连接操作

package com.web.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//定义一个获取数据库链接的工具类
public class DataSources {
    // 提供链接的信息 jdbc:mysql://IP地址:端口号/访问的数据库?useUnicode=true&characterEncoding=utf8
    static String url = "jdbc:mysql://localhost:3306/食品溯源系统?useUnicode=true&characterEncoding=utf8";
    static String user = "root";
    static String password = "1234";

    static {
        // 使用反射来加载jdbc的驱动类
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 一个获取链接的静态方法
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 一个关闭链接的静态方法
    public static void closeConnection(Connection con) {
        // 非空校验
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

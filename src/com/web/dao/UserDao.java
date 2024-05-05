//该模块用于实现用户管理(登录、注册等)相应操作

package com.web.dao;

import com.web.bean.User;
import com.web.util.DataSources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author byte~
 * @version 1.0
 */
//用户模块的数据库操作类
public class UserDao {

    //定义一个方法来实现登录操作操作
    public User queryUserLogin(String id, String password) {
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        // 2、定义查询的sql语句
        String sql = "select * from food_user where u_id=? and u_password=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, id);
            ps.setString(2, password);
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、得到结果
            if (result.next()) {//判断结果集中是否有下一条记录
                //得到一行的数据
                String Id = result.getString("u_id");
                String nickname = result.getString("u_nickname");
                String Password = result.getString("u_password");
                String name = result.getString("u_name");
                String sex = result.getString("u_sex");
                int position = result.getInt("u_position");
                String phone = result.getString("u_phone");
                String time = result.getString("u_time");
                //将数据封装到Java对象中
                User user = new User(Id, nickname, Password, name, sex, position, phone, time);
                switch (position){
                    case 0:user.setU_position_name("超级管理员");break;
                    case 1:user.setU_position_name("生产商");break;
                    case 2:user.setU_position_name("运输商");break;
                    case 3:user.setU_position_name("销售商");break;
                    default:user.setU_position_name("消费者");
                }
                DataSources.closeConnection(connection);
                return user;
            } else {
                System.out.println("查无此人");
                DataSources.closeConnection(connection);
                return null;
            }
        } catch (SQLException throwables) {
            DataSources.closeConnection(connection);
            throwables.printStackTrace();
        }
        return null;
    }
    //定义一个方法来实现注册操作
    public boolean queryRegister(String id,String nickname,String password,String name,String sex,int position,String phone){
        boolean ok = checkid(id);
        if(!ok) return false;
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 2、定义添加的sql语句
        String sql = "insert into food_user values(?,?,?,?,?,?,?,?);";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,id);
            ps.setString(2,nickname);
            ps.setString(3,password);
            ps.setString(4,name);
            ps.setString(5,sex);
            ps.setInt(6,position);
            ps.setString(7,phone);
            ps.setString(8,date.format(formatter));
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ps.executeUpdate();//执行添加
            System.out.println("添加成功！");
            // 5、得到结果
            DataSources.closeConnection(connection);
        } catch (SQLException throwables) {
            DataSources.closeConnection(connection);
            throwables.printStackTrace();
        }
        return true;
    }
    //定义一个方法检查是否有重复id
    public boolean checkid(String userId){
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        // 2、定义查询是否有相同id的sql语句
        String sql = "select * from food_user where u_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, userId);
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、得到结果
            if (result.next()) {//判断结果集中是否有下一条记录
                System.out.println("有同id，不可创建！！！");
                DataSources.closeConnection(connection);
                return false;
            } else {
                System.out.println("无同id，可创建！");
                DataSources.closeConnection(connection);
            }
        } catch (SQLException throwables) {
            DataSources.closeConnection(connection);
            throwables.printStackTrace();
        }
        return true;
    }
    //定义一个方法来实现查询所有的员工
    public List<User> queryUser() {
        //创建一个集合用于存放数据
        List<User> list = new ArrayList<>();
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        // 2、定义查询的sql语句
        String sql = "select * from food_user,positions where p_id = u_position order by u_id+0 asc;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、循环从result得到结果
            while (result.next()) {//判断结果集中是否有下一条记录
                //得到一行的数据
                String id = result.getString("u_id");
                String nickname = result.getString("u_nickname");
                String password = result.getString("u_password");
                String name = result.getString("u_name");
                String sex = result.getString("u_sex");
                int position = result.getInt("u_position");
                String phone = result.getString("u_phone");
                String time = result.getString("u_time");
                //获取职位名
                String p_name = result.getString("p_name");
                //将数据封装到Java对象中
                User user = new User(id, nickname, password, name, sex, position, phone, time);
                user.setU_position_name(p_name);
                //将对象存放到list中
                list.add(user);
            }
            DataSources.closeConnection(connection);
        } catch (SQLException throwables) {
            DataSources.closeConnection(connection);
            throwables.printStackTrace();
        }
        //返回集合
        return list;
    }
    //定义一个方法来更新全部员工信息
    public boolean updateUser(User user) {
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        // 2、定义查询的sql语句
        String sql = "update food_user set u_nickname=?,u_password=?,u_name=?,u_sex=?,u_phone=? where u_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, user.getU_nickname());
            ps.setString(2, user.getU_password());
            ps.setString(3, user.getU_name());
            ps.setString(4, user.getU_sex());
            ps.setString(5, user.getU_phone());
            ps.setString(6, user.getU_id());
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）  查询操作使用executeQuery  更新操作
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
    //定义一个方法来更新员工个人信息
    public User updatePersonal(User user){
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        // 2、定义查询的sql语句
        String sql = "update food_user set u_nickname=?,u_name=?,u_sex=?,u_phone=? where u_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, user.getU_nickname());
            ps.setString(2, user.getU_name());
            ps.setString(3, user.getU_sex());
            ps.setString(4, user.getU_phone());
            ps.setString(5, user.getU_id());
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）  查询操作使用executeQuery  更新操作
            int result = ps.executeUpdate();//执行更新
            // 5、得到影响行数
            if (result > 0) {//判断影响行数是否大于0
                System.out.println("操作成功");
                String sql1 = "select * from food_user,positions where u_id=? and p_id = u_position;";
                // 1、获取sql语句对象  （通过链接来获取sql语句对象）
                PreparedStatement ps1 = null;
                ps1 = connection.prepareStatement(sql1);
                ps1.setString(1, user.getU_id());
                // 2、执行sql语句  （通过sql语句对象来执行sql语句 ）
                ResultSet result1 = ps1.executeQuery();//执行查询
                result1.next();
                //得到一行的数据
                String Id = result1.getString("u_id");
                String nickname = result1.getString("u_nickname");
                String Password = result1.getString("u_password");
                String name = result1.getString("u_name");
                String sex = result1.getString("u_sex");
                int position = result1.getInt("u_position");
                String phone = result1.getString("u_phone");
                String time = result1.getString("u_time");
                String u_position_name = result1.getString("p_name");
                //将数据封装到Java对象中
                User user1 = new User(Id, nickname, Password, name, sex, position, phone, time);
                user1.setU_position_name(u_position_name);
                DataSources.closeConnection(connection);
                return user1;
            } else {
                System.out.println("操作失败");
                DataSources.closeConnection(connection);
                return null;
            }
        } catch (SQLException throwables) {
            DataSources.closeConnection(connection);
            throwables.printStackTrace();
        }
        return null;
    }
    //定义一个方法来实现删除操作
    public boolean deleteUser(String userId){
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        // 2、定义查询的sql语句
        String sql = "select * from food_user where u_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, userId);
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、得到结果
            if (result.next()) {//判断结果集中是否有下一条记录
                //执行删除语句
                String sql1 = "Delete from food_user where u_id=?;";
                ps = connection.prepareStatement(sql1);
                // 给sql语句赋值
                ps.setString(1, userId);
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
    //定义一个方法来实现条件查询部门信息
    public List<User> Fuzzy_queryUser(User user){
        //创建一个集合用于存放数据
        List<User> list = new ArrayList<>();
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        // 2、定义模糊查询的sql语句
        String sql = "select * from food_user,positions where u_id like ? and" +
                " u_nickname like ? and u_name like ? and" +
                " u_phone like ? and p_id = u_position order by u_id+0 asc;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);

            // 给sql语句赋值
            ps.setString(1, "%"+user.getU_id()+"%");
            ps.setString(2, "%"+user.getU_nickname()+"%");
            ps.setString(3, "%"+user.getU_name()+"%");
            ps.setString(4,"%"+user.getU_phone()+"%");
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、循环从result得到结果
            while (result.next()) {//判断结果集中是否有下一条记录
                //得到一行的数据
                String u_id = result.getString("u_id");
                String u_nickname = result.getString("u_nickname");
                String u_password = result.getString("u_password");
                String u_name = result.getString("u_name");
                String u_sex = result.getString("u_sex");
                String u_position = result.getString("u_position");
                String u_phone = result.getString("u_phone");
                String u_time = result.getString("u_time");
                //获取职位名
                String p_name = result.getString("p_name");
                //将数据封装到Java对象中
                User u = new User(u_id, u_nickname, u_password, u_name, u_sex, Integer.parseInt(u_position), u_phone, u_time);
                u.setU_position_name(p_name);
                //将对象存放到list中
                list.add(u);
            }
            DataSources.closeConnection(connection);
        } catch (SQLException throwables) {
            DataSources.closeConnection(connection);
            throwables.printStackTrace();
        }
        //返回集合
        return list;
    }
    //定义一个方法来修改密码
    public boolean changePassowrd(String u_id,String u_password){
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        // 2、定义查询的sql语句
        String sql = "update food_user set u_password=? where u_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, u_password);
            ps.setString(2, u_id);
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
}
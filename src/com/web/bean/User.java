//该模块保存整个用户系统信息

package com.web.bean;

/**
 * @author byte~
 * @version 1.0
 */

public class User {
    private String u_id;//编号
    private String u_nickname;//昵称
    private String u_password;//密码
    private String u_name;//姓名
    private String u_sex;//性别
    private int u_position;//工作职位id
    private String u_phone;//手机号码
    private String u_time;//创建时间-入职时间
    //工作职位id的关联-- 名称
    private String u_position_name;//职位名

    public User(String u_id, String u_nickname, String u_password, String u_name, String u_sex, int u_position, String u_phone, String u_time) {
        this.u_id = u_id;
        this.u_nickname = u_nickname;
        this.u_password = u_password;
        this.u_name = u_name;
        this.u_sex = u_sex;
        this.u_position = u_position;
        this.u_phone = u_phone;
        this.u_time = u_time;
    }

    @Override
    public String toString() {
        return "User{" +
                "u_id='" + u_id + '\'' +
                ", u_nickname='" + u_nickname + '\'' +
                ", u_password='" + u_password + '\'' +
                ", u_name='" + u_name + '\'' +
                ", u_sex='" + u_sex + '\'' +
                ", u_position=" + u_position +
                ", u_phone='" + u_phone + '\'' +
                ", u_time='" + u_time + '\'' +
                ", u_position_name='" + u_position_name + '\'' +
                '}';
    }

    public String getU_position_name() {
        return u_position_name;
    }

    public void setU_position_name(String u_position_name) {
        this.u_position_name = u_position_name;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getU_nickname() {
        return u_nickname;
    }

    public void setU_nickname(String u_nickname) {
        this.u_nickname = u_nickname;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_sex() {
        return u_sex;
    }

    public void setU_sex(String u_sex) {
        this.u_sex = u_sex;
    }

    public int getU_position() {
        return u_position;
    }

    public void setU_position(int u_position) {
        this.u_position = u_position;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public String getU_time() {
        return u_time;
    }

    public void setU_time(String u_time) {
        this.u_time = u_time;
    }
}

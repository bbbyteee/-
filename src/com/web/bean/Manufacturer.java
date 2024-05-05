//该模块保存生产商信息

package com.web.bean;

/**
 * @author byte~
 * @version 1.0
 */

public class Manufacturer {
    private String m_id;//编号
    private String m_create_date;//创建日期
    private String m_operator;//经办人
    private String m_phone;//电话
    private String m_mail;//邮箱
    private String m_foodname;//食品名称
    private String m_origin_place;//产地
    private String m_date_manufacture;//生产日期
    private String m_in_date;//入库日期
    private String m_packaging_date;//打包日期
    private String m_out_date;//出库日期
    private String m_weather;//气候
    private String u_id;//用户id

    public Manufacturer(String m_id, String m_create_date, String m_operator, String m_phone, String m_mail, String m_foodname, String m_origin_place, String m_date_manufacture, String m_in_date, String m_packaging_date, String m_out_date, String m_weather, String u_id) {
        this.m_id = m_id;
        this.m_create_date = m_create_date;
        this.m_operator = m_operator;
        this.m_phone = m_phone;
        this.m_mail = m_mail;
        this.m_foodname = m_foodname;
        this.m_origin_place = m_origin_place;
        this.m_date_manufacture = m_date_manufacture;
        this.m_in_date = m_in_date;
        this.m_packaging_date = m_packaging_date;
        this.m_out_date = m_out_date;
        this.m_weather = m_weather;
        this.u_id = u_id;
    }

    public Manufacturer(String m_operator) {
        this.m_operator = m_operator;
    }

    @Override
    public String toString() {
        return "manufacturer{" +
                "m_id='" + m_id + '\'' +
                ", m_create_date='" + m_create_date + '\'' +
                ", m_operator='" + m_operator + '\'' +
                ", m_phone='" + m_phone + '\'' +
                ", m_mail='" + m_mail + '\'' +
                ", m_foodname='" + m_foodname + '\'' +
                ", m_origin_place='" + m_origin_place + '\'' +
                ", m_date_manufacture='" + m_date_manufacture + '\'' +
                ", m_in_date='" + m_in_date + '\'' +
                ", m_packaging_date='" + m_packaging_date + '\'' +
                ", m_out_date='" + m_out_date + '\'' +
                ", m_weather='" + m_weather + '\'' +
                ", u_id='" + u_id + '\'' +
                '}';
    }

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getM_create_date() {
        return m_create_date;
    }

    public void setM_create_date(String m_create_date) {
        this.m_create_date = m_create_date;
    }

    public String getM_operator() {
        return m_operator;
    }

    public void setM_operator(String m_operator) {
        this.m_operator = m_operator;
    }

    public String getM_phone() {
        return m_phone;
    }

    public void setM_phone(String m_phone) {
        this.m_phone = m_phone;
    }

    public String getM_mail() {
        return m_mail;
    }

    public void setM_mail(String m_mail) {
        this.m_mail = m_mail;
    }

    public String getM_foodname() {
        return m_foodname;
    }

    public void setM_foodname(String m_foodname) {
        this.m_foodname = m_foodname;
    }

    public String getM_origin_place() {
        return m_origin_place;
    }

    public void setM_origin_place(String m_origin_place) {
        this.m_origin_place = m_origin_place;
    }

    public String getM_date_manufacture() {
        return m_date_manufacture;
    }

    public void setM_date_manufacture(String m_date_manufacture) {
        this.m_date_manufacture = m_date_manufacture;
    }

    public String getM_in_date() {
        return m_in_date;
    }

    public void setM_in_date(String m_in_date) {
        this.m_in_date = m_in_date;
    }

    public String getM_packaging_date() {
        return m_packaging_date;
    }

    public void setM_packaging_date(String m_packaging_date) {
        this.m_packaging_date = m_packaging_date;
    }

    public String getM_out_date() {
        return m_out_date;
    }

    public void setM_out_date(String m_out_date) {
        this.m_out_date = m_out_date;
    }

    public String getM_weather() {
        return m_weather;
    }

    public void setM_weather(String m_weather) {
        this.m_weather = m_weather;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }
}

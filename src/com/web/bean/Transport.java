//该模块保存运输商信息

package com.web.bean;

/**
 * @author byte~
 * @version 1.0
 */

public class Transport {
    private String t_id;//编号
    private String t_create_date;//创建日期
    private String t_name;//单位名称
    private String t_operator;//负责人
    private String t_phone;//电话
    private String t_address;//地址
    private String t_in_date;//入库日期
    private String t_out_date;//出库日期
    private String t_type_transportation;//运输方式
    private String t_destination;//主要流向
    private String u_id;//用户id
    private String m_id;//食品id

    public Transport(String t_id, String t_create_date, String t_name,
                     String t_operator, String t_phone, String t_address,
                     String t_in_date, String t_out_date,
                     String t_type_transportation, String t_destination,
                     String u_id, String m_id) {
        this.t_id = t_id;
        this.t_create_date = t_create_date;
        this.t_name = t_name;
        this.t_operator = t_operator;
        this.t_phone = t_phone;
        this.t_address = t_address;
        this.t_in_date = t_in_date;
        this.t_out_date = t_out_date;
        this.t_type_transportation = t_type_transportation;
        this.t_destination = t_destination;
        this.u_id = u_id;
        this.m_id = m_id;
    }

    public Transport(String t_name,String t_id) {
        this.t_name = t_name;
        this.t_id = t_id;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "t_id='" + t_id + '\'' +
                ", t_create_date='" + t_create_date + '\'' +
                ", t_name='" + t_name + '\'' +
                ", t_operator='" + t_operator + '\'' +
                ", t_phone='" + t_phone + '\'' +
                ", t_address='" + t_address + '\'' +
                ", t_in_date='" + t_in_date + '\'' +
                ", t_out_date='" + t_out_date + '\'' +
                ", t_type_transportation='" + t_type_transportation + '\'' +
                ", t_destination='" + t_destination + '\'' +
                ", u_id='" + u_id + '\'' +
                ", m_id='" + m_id + '\'' +
                '}';
    }

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getT_create_date() {
        return t_create_date;
    }

    public void setT_create_date(String t_create_date) {
        this.t_create_date = t_create_date;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getT_operator() {
        return t_operator;
    }

    public void setT_operator(String t_operator) {
        this.t_operator = t_operator;
    }

    public String getT_phone() {
        return t_phone;
    }

    public void setT_phone(String t_phone) {
        this.t_phone = t_phone;
    }

    public String getT_address() {
        return t_address;
    }

    public void setT_address(String t_address) {
        this.t_address = t_address;
    }

    public String getT_in_date() {
        return t_in_date;
    }

    public void setT_in_date(String t_in_date) {
        this.t_in_date = t_in_date;
    }

    public String getT_out_date() {
        return t_out_date;
    }

    public void setT_out_date(String t_out_date) {
        this.t_out_date = t_out_date;
    }

    public String getT_type_transportation() {
        return t_type_transportation;
    }

    public void setT_type_transportation(String t_type_transportation) {
        this.t_type_transportation = t_type_transportation;
    }

    public String getT_destination() {
        return t_destination;
    }

    public void setT_destination(String t_destination) {
        this.t_destination = t_destination;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }
}

//该模块保存销售商信息

package com.web.bean;

/**
 * @author byte~
 * @version 1.0
 */

public class Seller {
    private String s_id;//编号
    private String s_create_date;//创建日期
    private String s_seller;//经销商
    private String s_COVID19_Report;//新冠检测
    private String s_in_date;//入库日期
    private String s_out_date;//出库日期
    private String s_launch_date;//上架日期
    private String u_id;//用户id
    private String m_id;//食品id

    public Seller(String s_id, String s_create_date, String s_seller, String s_COVID19_Report, String s_in_date, String s_out_date, String s_launch_date, String u_id, String m_id) {
        this.s_id = s_id;
        this.s_create_date = s_create_date;
        this.s_seller = s_seller;
        this.s_COVID19_Report = s_COVID19_Report;
        this.s_in_date = s_in_date;
        this.s_out_date = s_out_date;
        this.s_launch_date = s_launch_date;
        this.u_id = u_id;
        this.m_id = m_id;
    }

    public Seller(String s_id, String s_seller) {
        this.s_id = s_id;
        this.s_seller = s_seller;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "s_id='" + s_id + '\'' +
                ", s_create_date='" + s_create_date + '\'' +
                ", s_seller='" + s_seller + '\'' +
                ", s_COVID19_Report='" + s_COVID19_Report + '\'' +
                ", s_in_date='" + s_in_date + '\'' +
                ", s_out_date='" + s_out_date + '\'' +
                ", s_launch_date='" + s_launch_date + '\'' +
                ", u_id='" + u_id + '\'' +
                ", m_id='" + m_id + '\'' +
                '}';
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getS_create_date() {
        return s_create_date;
    }

    public void setS_create_date(String s_create_date) {
        this.s_create_date = s_create_date;
    }

    public String gets_seller() {
        return s_seller;
    }

    public void sets_seller(String s_seller) {
        this.s_seller = s_seller;
    }

    public String getS_COVID19_Report() {
        return s_COVID19_Report;
    }

    public void setS_COVID19_Report(String s_COVID19_Report) {
        this.s_COVID19_Report = s_COVID19_Report;
    }

    public String getS_in_date() {
        return s_in_date;
    }

    public void setS_in_date(String s_in_date) {
        this.s_in_date = s_in_date;
    }

    public String getS_out_date() {
        return s_out_date;
    }

    public void setS_out_date(String s_out_date) {
        this.s_out_date = s_out_date;
    }

    public String getS_launch_date() {
        return s_launch_date;
    }

    public void setS_launch_date(String s_launch_date) {
        this.s_launch_date = s_launch_date;
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

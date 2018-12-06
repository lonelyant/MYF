package com.myf.show.model;

/**
 * @author: Ant
 * @Date: 2018/10/23 13:47
 * @Description: 产品实体类
 */
public class Product {
    private int id;
    private String p_name;
    private double p_price;
    private String p_category;
    private String p_imgurl;
    private String p_desc;
    private int p_isShow;
    private int p_order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public double getP_price() {
        return p_price;
    }

    public void setP_price(double p_price) {
        this.p_price = p_price;
    }

    public String getP_category() {
        return p_category;
    }

    public void setP_category(String p_category) {
        this.p_category = p_category;
    }

    public String getP_imgurl() {
        return p_imgurl;
    }

    public void setP_imgurl(String p_imgurl) {
        this.p_imgurl = p_imgurl;
    }

    public String getP_desc() {
        return p_desc;
    }

    public void setP_desc(String p_desc) {
        this.p_desc = p_desc;
    }

    public int getP_isShow() {
        return p_isShow;
    }

    public void setP_isShow(int p_isShow) {
        this.p_isShow = p_isShow;
    }

    public int getP_order() {
        return p_order;
    }

    public void setP_order(int p_order) {
        this.p_order = p_order;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", p_name='" + p_name + '\'' +
                ", p_price=" + p_price +
                ", p_category='" + p_category + '\'' +
                ", p_imgurl='" + p_imgurl + '\'' +
                ", p_desc='" + p_desc + '\'' +
                ", p_isShow=" + p_isShow +
                ", p_order=" + p_order +
                '}';
    }
}

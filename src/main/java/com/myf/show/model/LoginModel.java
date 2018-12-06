package com.myf.show.model;

/**
 * @author: Ant
 * @Date: 2018/09/17 18:19
 * @Description: 登录实体类
 */
public class LoginModel {
    private Boolean status;//登录是否成功
    private String info;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }



    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "status=" + status +
                ", info='" + info + '\'' +
                '}';
    }
}

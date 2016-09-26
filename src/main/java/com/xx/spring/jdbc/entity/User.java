package com.xx.spring.jdbc.entity;

import java.util.Date;

/**
 * Created by wxiao on 2016.9.19.
 *
 * 用户实体类
 *
 */

public class User {


    /**
     * 唯一标识符
     */
    private String id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 状态 0-正常 1-禁用 -1-删除
     */
    private int status;


    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 空构造器
     */
    public User() {}


    /**
     * 构造器一
     * @param name 用户名
     * @param pwd 密码
     */
    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", status=" + status +
                ", creatTime=" + creatTime +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

}

package com.tulingxyuan.entity;

import org.springframework.stereotype.Component;

/**
 * Created by ChenCF on 2021/9/29
 */
@Component
public class User {


    private Integer id;
    private String username;
    private String realname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }


    //先加无参构造
    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                '}';
    }

    //再加有参构造
    public User(Integer id, String username, String realname) {
        this.id = id;
        this.username = username;
        this.realname = realname;
    }

    public User() {
       // System.out.println("user 已经实例化开始了");
    }


}

package com.study.mybatis.domain;

import java.io.Serializable;

/**
 * @author Harlan
 * @date 2020/8/6 18:31
 */
public class Account implements Serializable {

    private Integer id;
    private Integer uid;
    private Double money;
    private User user;
    //从表实体应该包含一个主表实体的对象引用

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", uid=" + uid +
                ", money=" + money +
                '}';
    }
}

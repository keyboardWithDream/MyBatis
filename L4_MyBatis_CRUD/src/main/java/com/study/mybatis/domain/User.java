package com.study.mybatis.domain;

import java.util.Date;

/**
 * @author Harlan
 * @date 2020/8/5 6:18
 */
public class User {

    private Integer uid;
    private String uName;
    private String uAddress;
    private String uSex;
    private Date uBirthday;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuAddress() {
        return uAddress;
    }

    public void setuAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    public String getuSex() {
        return uSex;
    }

    public void setuSex(String uSex) {
        this.uSex = uSex;
    }

    public Date getuBirthday() {
        return uBirthday;
    }

    public void setuBirthday(Date uBirthday) {
        this.uBirthday = uBirthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uName='" + uName + '\'' +
                ", uAddress='" + uAddress + '\'' +
                ", uSex='" + uSex + '\'' +
                ", uBirthday=" + uBirthday +
                '}';
    }
}

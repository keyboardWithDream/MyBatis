package com.study.mybatis.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Harlan
 * @date 2020/8/12 17:52
 */
public class User implements Serializable {

    private Integer uId;
    private String uName;
    private String uAddress;
    private String uSex;
    private Date uBirthday;
    private List<Account> accounts;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
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
                "uId=" + uId +
                ", uName='" + uName + '\'' +
                ", uAddress='" + uAddress + '\'' +
                ", uSex='" + uSex + '\'' +
                ", uBirthday=" + uBirthday +
                '}';
    }
}

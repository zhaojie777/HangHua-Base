package com.beetle.hanghuasso.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private static final long serialVersionUID = 3967104638645809690L;

    private int id;
    private String account;                             //账号
    private String passWord;                            //密码
    private String nickName;                            //昵称
    private Date birtyDay;                              //生日
    private String email;                               //邮箱
    private String avatar;                              //头像地址
    private int level;                                  //等级
    private int credit;                                 //信用
    private int accountStatus;                          //账号状态
    private Date lastLoginTime;                         //最后登陆时间
    private int login_type;                             //登陆类型

    public User() {}

    public User(int id, String account, String passWord,
                String nickName, Date birtyDay, String email,
                String avatar, int level, int credit,
                int accountStatus, Date lastLoginTime, int login_type) {
        this.id = id;
        this.account = account;
        this.passWord = passWord;
        this.nickName = nickName;
        this.birtyDay = birtyDay;
        this.email = email;
        this.avatar = avatar;
        this.level = level;
        this.credit = credit;
        this.accountStatus = accountStatus;
        this.lastLoginTime = lastLoginTime;
        this.login_type = login_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getBirtyDay() {
        return birtyDay;
    }

    public void setBirtyDay(Date birtyDay) {
        this.birtyDay = birtyDay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getLogin_type() {
        return login_type;
    }

    public void setLogin_type(int login_type) {
        this.login_type = login_type;
    }
}

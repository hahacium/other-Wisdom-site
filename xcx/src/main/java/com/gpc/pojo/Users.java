package com.gpc.pojo;

import javax.persistence.*;

public class Users {
    /**
     * 用户id
     */
    @Id
    private String id;

    /**
     * 工号
     */
    private String number;

    /**
     * 密码
     */
    private String password;

    private String phone;

    private String name;

    @Column(name = "id_card")
    private String idCard;

    /**
     * 获取用户id
     *
     * @return id - 用户id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置用户id
     *
     * @param id 用户id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取工号
     *
     * @return number - 工号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置工号
     *
     * @param number 工号
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return id_card
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * @param idCard
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
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
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话
     */
    private String phone;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证
     */
    @Column(name = "id_card")
    private String idCard;

    /**
     * 用户头像
     */
    @Column(name = "face_image")
    private String faceImage;

    /**
     * 0-员工 1管理员
     */
    private Integer status;

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
     * @return username - 工号
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置工号
     *
     * @param username 工号
     */
    public void setUsername(String username) {
        this.username = username;
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
     * 获取电话
     *
     * @return phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取身份证
     *
     * @return id_card - 身份证
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * 设置身份证
     *
     * @param idCard 身份证
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * 获取用户头像
     *
     * @return face_image - 用户头像
     */
    public String getFaceImage() {
        return faceImage;
    }

    /**
     * 设置用户头像
     *
     * @param faceImage 用户头像
     */
    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    /**
     * 获取0-员工 1管理员
     *
     * @return status - 0-员工 1管理员
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0-员工 1管理员
     *
     * @param status 0-员工 1管理员
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}
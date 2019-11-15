package com.gpc.pojo;

import javax.persistence.*;

public class Picture {
    @Id
    private String id;

    private String pid;

    private String name;

    private Picture parent;

    public Picture getParent() {
        return parent;
    }

    public void setParent(Picture parent) {
        this.parent = parent;
    }

    /**
     * 工程图纸
     */
    private String image;

    /**
     * 设计者
     */
    private String designer;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(String pid) {
        this.pid = pid;
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
     * 获取工程图纸
     *
     * @return image - 工程图纸
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置工程图纸
     *
     * @param image 工程图纸
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取设计者
     *
     * @return designer - 设计者
     */
    public String getDesigner() {
        return designer;
    }

    /**
     * 设置设计者
     *
     * @param designer 设计者
     */
    public void setDesigner(String designer) {
        this.designer = designer;
    }
}
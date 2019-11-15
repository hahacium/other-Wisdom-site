package com.gpc.pojo.vo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "department_employee")
public class DepartmentEmployeeVo {
    @Id
    private String id;

    /**
     * 父id
     */
    private String pid;

    /**
     * 栏目名称
     */
    @Column(name = "label_name")
    private String labelName;

    private String faceImage;

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

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
     * 获取父id
     *
     * @return pid - 父id
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置父id
     *
     * @param pid 父id
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取栏目名称
     *
     * @return label_name - 栏目名称
     */
    public String getLabelName() {
        return labelName;
    }

    /**
     * 设置栏目名称
     *
     * @param labelName 栏目名称
     */
    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
}
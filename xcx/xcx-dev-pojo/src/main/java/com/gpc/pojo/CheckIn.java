package com.gpc.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "check_in")
public class CheckIn {
    @Id
    private String id;

    @Column(name = "user_id")
    private String userId;

    /**
     * 签到时间
     */
    @Column(name = "finish_time")
    private Date finishTime;

    /**
     * 签到时间
     */
    @Column(name = "finish_time_str")
    private Integer finishTimeStr;


    @Column(name = "time_str")
    private String timeStr;

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    /**
     * 1人脸2现场
     */
    private Integer status;

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
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取签到时间
     *
     * @return finish_time - 签到时间
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * 设置签到时间
     *
     * @param finishTime 签到时间
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getFinishTimeStr() {
        return finishTimeStr;
    }

    public void setFinishTimeStr(Integer finishTimeStr) {
        this.finishTimeStr = finishTimeStr;
    }

    /**
     * 获取0未签到，1已签到
     *
     * @return status - 0未签到，1已签到
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0未签到，1已签到
     *
     * @param status 0未签到，1已签到
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}
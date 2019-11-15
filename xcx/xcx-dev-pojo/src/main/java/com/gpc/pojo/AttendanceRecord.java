package com.gpc.pojo;

import javax.persistence.*;
import java.util.List;

@Table(name = "attendance_record")
public class AttendanceRecord {
    @Id
    private String id;

    private String uid;

    @Column(name = "month_time")
    private Integer monthTime;

    private Integer sum;

    private List<CheckIn> checkIn;

    public List<CheckIn> getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(List<CheckIn> checkIn) {
        this.checkIn = checkIn;
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
     * @return uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return month_time
     */
    public Integer getMonthTime() {
        return monthTime;
    }

    /**
     * @param monthTime
     */
    public void setMonthTime(Integer monthTime) {
        this.monthTime = monthTime;
    }

    /**
     * @return sum
     */
    public Integer getSum() {
        return sum;
    }

    /**
     * @param sum
     */
    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
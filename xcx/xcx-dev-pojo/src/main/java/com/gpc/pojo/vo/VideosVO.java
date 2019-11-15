package com.gpc.pojo.vo;

import javax.persistence.Column;
import javax.persistence.Id;

public class VideosVO {
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 视频描述
     */
    @Column(name = "video_desc")
    private String videoDesc;

    /**
     * 视频路径
     */
    @Column(name = "video_path")
    private String videoPath;

    /**
     * 视频秒数
     */
    @Column(name = "video_seconds")
    private Float videoSeconds;

    /**
     * 视频宽度
     */
    @Column(name = "video_width")
    private Integer videoWidth;

    /**
     * 视频高度
     */
    @Column(name = "video_height")
    private Integer videoHeight;

    /**
     * 封面图路径
     */
    @Column(name = "cover_path")
    private String coverPath;

    /**
     * 1-已发布，0禁止(管理操作)
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    private String name;
    private String faceImage;
    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取视频描述
     *
     * @return video_desc - 视频描述
     */
    public String getVideoDesc() {
        return videoDesc;
    }

    /**
     * 设置视频描述
     *
     * @param videoDesc 视频描述
     */
    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    /**
     * 获取视频路径
     *
     * @return video_path - 视频路径
     */
    public String getVideoPath() {
        return videoPath;
    }

    /**
     * 设置视频路径
     *
     * @param videoPath 视频路径
     */
    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    /**
     * 获取视频秒数
     *
     * @return video_seconds - 视频秒数
     */
    public Float getVideoSeconds() {
        return videoSeconds;
    }

    /**
     * 设置视频秒数
     *
     * @param videoSeconds 视频秒数
     */
    public void setVideoSeconds(Float videoSeconds) {
        this.videoSeconds = videoSeconds;
    }

    /**
     * 获取视频宽度
     *
     * @return video_width - 视频宽度
     */
    public Integer getVideoWidth() {
        return videoWidth;
    }

    /**
     * 设置视频宽度
     *
     * @param videoWidth 视频宽度
     */
    public void setVideoWidth(Integer videoWidth) {
        this.videoWidth = videoWidth;
    }

    /**
     * 获取视频高度
     *
     * @return video_height - 视频高度
     */
    public Integer getVideoHeight() {
        return videoHeight;
    }

    /**
     * 设置视频高度
     *
     * @param videoHeight 视频高度
     */
    public void setVideoHeight(Integer videoHeight) {
        this.videoHeight = videoHeight;
    }

    /**
     * 获取封面图路径
     *
     * @return cover_path - 封面图路径
     */
    public String getCoverPath() {
        return coverPath;
    }

    /**
     * 设置封面图路径
     *
     * @param coverPath 封面图路径
     */
    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    /**
     * 获取1-已发布，0禁止(管理操作)
     *
     * @return status - 1-已发布，0禁止(管理操作)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置1-已发布，0禁止(管理操作)
     *
     * @param status 1-已发布，0禁止(管理操作)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
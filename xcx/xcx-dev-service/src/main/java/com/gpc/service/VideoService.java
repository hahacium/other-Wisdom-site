package com.gpc.service;

import com.gpc.pojo.Videos;
import com.gpc.utils.PagedResult;

import java.util.List;

public interface VideoService {
    //上传视频
    public void uploadVideo(Videos videos);

    //视频列表
    PagedResult getAllVideos(Videos video, Integer isSaveRecord, Integer page, Integer pageSize);

    /**
     * @Description: 获取热搜词列表
     */
    public List<String> getHotwords();
}

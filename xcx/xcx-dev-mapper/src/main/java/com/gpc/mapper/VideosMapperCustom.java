package com.gpc.mapper;

import com.gpc.pojo.vo.VideosVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideosMapperCustom {
    List<VideosVO> queryAllVideos(@Param("videoDesc") String desc);
}

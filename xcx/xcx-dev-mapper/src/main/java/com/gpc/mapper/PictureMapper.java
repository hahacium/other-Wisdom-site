package com.gpc.mapper;

import com.gpc.pojo.Picture;
import com.gpc.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PictureMapper extends MyMapper<Picture> {
    /**
     * 图纸搜索
     * @param name
     * @return
     */
    List<Picture> searchPic(@Param("name") String name);
}
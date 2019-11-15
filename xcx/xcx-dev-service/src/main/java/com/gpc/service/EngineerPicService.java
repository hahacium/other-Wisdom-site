package com.gpc.service;

import com.gpc.pojo.Picture;

import java.util.List;

public interface EngineerPicService {

    /**
     * 查询一级分类
     * @return
     */
    List<Picture> selectFirst();

    /**
     * 根据一级分类查询二级分类
     * @param id
     * @return
     */
    List<Picture> selectSecond(String id);


    /**
     * 根据id查询工程图纸
     * @param id
     * @return
     */
    Picture selectPic(String id);

    /**
     * 图纸搜索
     * @param name
     * @return
     */
    List<Picture> searchPic(String name);
}

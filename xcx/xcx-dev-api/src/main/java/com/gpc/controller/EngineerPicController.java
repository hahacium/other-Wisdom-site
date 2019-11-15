package com.gpc.controller;

import com.gpc.pojo.Picture;
import com.gpc.service.EngineerPicService;
import com.gpc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("pic")
public class EngineerPicController {
    @Autowired
    private EngineerPicService engineerPicService;
    /**
     * 查询一级栏目
     */
    @RequestMapping("/selectFirst")
    public JsonResult selectFirst() {
        return JsonResult.ok(engineerPicService.selectFirst());
    }

    /**
     * 根据一级分类查询二级分类
     */
    @RequestMapping("/selectSecond")
    public JsonResult selectSecond(String id) {
        List<Picture> list = engineerPicService.selectSecond(id);
        return JsonResult.ok(list);
    }

    /**
     * 根据id查询工程图
     */
    @RequestMapping("/selectPic")
    public JsonResult selectPic(String id) {
        Picture picture = engineerPicService.selectPic(id);
        return JsonResult.ok(picture);
    }


    /**
     * 图纸搜索
     */
    @RequestMapping("/searchPic/{name}")
    public JsonResult searchPic(@PathVariable String name) {
        List<Picture> list = engineerPicService.searchPic(name);
        return JsonResult.ok(list);
    }
}

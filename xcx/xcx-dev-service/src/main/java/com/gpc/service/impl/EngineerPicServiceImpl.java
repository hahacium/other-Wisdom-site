package com.gpc.service.impl;

import com.gpc.mapper.PictureMapper;
import com.gpc.pojo.Picture;
import com.gpc.service.EngineerPicService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@Service
public class EngineerPicServiceImpl implements EngineerPicService {
    @Autowired
    private PictureMapper pictureMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Picture> selectFirst() {
        Example example = new Example(Picture.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIsNull("pid");
        return pictureMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Picture> selectSecond(String id) {
        if(StringUtils.isBlank(id)) {
            Example example = new Example(Picture.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andIsNotNull("pid");
            return pictureMapper.selectByExample(example);
        }else {
            Example example = new Example(Picture.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("pid",id);
            return pictureMapper.selectByExample(example);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Picture selectPic(String id) {
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Picture> searchPic(String name) {
        List<Picture> list = pictureMapper.searchPic(name);
        return list;
    }


}

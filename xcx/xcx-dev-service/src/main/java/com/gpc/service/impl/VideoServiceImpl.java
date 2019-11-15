package com.gpc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gpc.mapper.SearchRecordsMapper;
import com.gpc.mapper.VideosMapper;
import com.gpc.mapper.VideosMapperCustom;
import com.gpc.pojo.SearchRecords;
import com.gpc.pojo.Videos;
import com.gpc.pojo.vo.VideosVO;
import com.gpc.service.VideoService;
import com.gpc.utils.PagedResult;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideosMapper videosMapper;
    @Autowired
    private Sid sid;
    @Autowired
    private SearchRecordsMapper searchRecordsMapper;
    @Autowired
    private VideosMapperCustom videosMapperCustom;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void uploadVideo(Videos videos) {
        String id = sid.nextShort();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data1 = sdf.format(date);
        videos.setId(id);
        videos.setCreateTime(data1);
        videosMapper.insert(videos);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public PagedResult getAllVideos(Videos video, Integer isSaveRecord, Integer page, Integer pageSize) {
        // 保存热搜词
        String desc = video.getVideoDesc();
        String userId = video.getUserId();
        if (isSaveRecord != null && isSaveRecord == 1) {
            SearchRecords record = new SearchRecords();
            String recordId = sid.nextShort();
            record.setId(recordId);
            record.setContent(desc);
            searchRecordsMapper.insert(record);
        }
        PageHelper.startPage(page,pageSize);
        List<VideosVO> list = videosMapperCustom.queryAllVideos(desc);
        PageInfo<VideosVO> pageList = new PageInfo(list);
        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(list);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<String> getHotwords() {
        return searchRecordsMapper.getHotwords();
    }
}

package com.gpc.service.impl;

import com.gpc.mapper.SiteCheckInMapper;
import com.gpc.pojo.SiteCheckIn;
import com.gpc.service.SiteCheckInService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Service
public class SiteCheckInServiceImpl implements SiteCheckInService {
    @Autowired
    private Sid sid;
    @Autowired
    private SiteCheckInMapper siteCheckInMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int insert(HashMap<String, String> paramMap) {
        String id = sid.nextShort();
        SiteCheckIn siteCheckIn = new SiteCheckIn();
        siteCheckIn.setId(id);
        siteCheckIn.setUid(paramMap.get("uid"));
        siteCheckIn.setAddress(paramMap.get("address"));
        siteCheckIn.setImage(paramMap.get("path"));
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2 = sdf.format(date);
        siteCheckIn.setTime(date2);
        int insert = siteCheckInMapper.insert(siteCheckIn);
        return insert;
    }
}

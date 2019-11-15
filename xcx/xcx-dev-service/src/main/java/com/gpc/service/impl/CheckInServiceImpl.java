package com.gpc.service.impl;

import com.gpc.mapper.AttendanceRecordMapper;
import com.gpc.mapper.CheckInMapper;
import com.gpc.pojo.AttendanceRecord;
import com.gpc.pojo.CheckIn;
import com.gpc.service.CheckInService;
import com.gpc.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class CheckInServiceImpl implements CheckInService {

    @Autowired
    private Sid sid;

    @Autowired
    private CheckInMapper checkInMapper;

    @Autowired
    private AttendanceRecordMapper attendanceRecordMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public CheckIn insertRecord(String id,String data) {
        String sid = this.sid.nextShort();
        CheckIn checkIn = new CheckIn();
        checkIn.setId(sid);
        checkIn.setUserId(id);
        Date date = new Date();
        checkIn.setFinishTime(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2 = sdf.format(date);
        checkIn.setTimeStr(date2);
        checkIn.setFinishTimeStr(date.getDate());
        if(StringUtils.isNotBlank(data)) {
            checkIn.setStatus(2);
        }else {
            checkIn.setStatus(1);
        }
        checkInMapper.insert(checkIn);
        List<CheckIn> checkIns = checkInMapper.selectById(id);
        Example example = new Example(AttendanceRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uid",id);
        AttendanceRecord attendanceRecord1 = attendanceRecordMapper.selectOneByExample(example);
        AttendanceRecord attendanceRecord = new AttendanceRecord();
        String s = this.sid.nextShort();
        //空说明第一次签到 赋值1
        if(attendanceRecord1 == null) {
            attendanceRecord.setId(s);
            attendanceRecord.setUid(id);
            attendanceRecord.setMonthTime(1);
            attendanceRecord.setSum(1);
            attendanceRecordMapper.insert(attendanceRecord);
        }else {
            /*CheckIn c = checkIns.get(0);
            Date date = c.getFinishTime();

            CheckIn c1 = checkIns.get(1);
            Date date1 = c.getFinishTime();
*/
            //获取最新签到记录
            CheckIn c = checkIns.get(0);
            Date date3 = c.getFinishTime();
            //获取天数 如果是一号，每月签到数赋值为1
            int day = date3.getDate();
            if(day == 01) {
                attendanceRecord.setMonthTime(1);
                attendanceRecord.setSum(attendanceRecord1.getSum() + 1);
                Example example1 =new Example(AttendanceRecord.class);
                Example.Criteria criteria1 = example1.createCriteria();
                criteria1.andEqualTo("uid",id);
                attendanceRecordMapper.updateByExampleSelective(attendanceRecord,example1);
            }else {
                //不是一号 累加
                attendanceRecord.setMonthTime(attendanceRecord1.getMonthTime() + 1);
                attendanceRecord.setSum(attendanceRecord1.getSum() + 1);
                Example example1 =new Example(AttendanceRecord.class);
                Example.Criteria criteria1 = example1.createCriteria();
                criteria1.andEqualTo("uid",id);
                attendanceRecordMapper.updateByExampleSelective(attendanceRecord,example1);
            }
            //Calendar calendar = Calendar.getInstance();
            //calendar.add(Calendar.DAY_OF_MONTH,1);
            //if(date.getMonth() + 1 != date1.getTime() + 1) {

            //}else {
                /*attendanceRecord.setMonthTime(attendanceRecord1.getMonthTime() + 1);
                attendanceRecord.setSum(attendanceRecord1.getSum() + 1);
                attendanceRecordMapper.insert(attendanceRecord);
            }
            attendanceRecordMapper.insert(attendanceRecord);*/
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public AttendanceRecord queryRecord(String id) {
        Example example = new Example(AttendanceRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uid",id);
        AttendanceRecord attendanceRecord = attendanceRecordMapper.selectOneByExample(example);
        return attendanceRecord;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public  List<CheckIn> queryCheckInTime(HashMap<String , Object> paramMap) {
        return checkInMapper.queryCheckInTime(paramMap);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CheckIn> queryCheckInDay(HashMap<String, Object> paramMap) {
        return checkInMapper.queryCheckInDay(paramMap);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public CheckIn queryCheckStatus(HashMap<String, Object> map) {
        return checkInMapper.queryCheckStatus(map);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void task () {
        Date date = new Date();
        if(date.getDate() == 01) {
            Example example = new Example(AttendanceRecord.class);
            Example.Criteria criteria = example.createCriteria();
            AttendanceRecord attendanceRecord = new AttendanceRecord();
            attendanceRecord.setMonthTime(0);
            attendanceRecordMapper.updateByExampleSelective(attendanceRecord,example);
        }
    }
}

package com.gpc.controller;

import com.gpc.pojo.AttendanceRecord;
import com.gpc.pojo.CheckIn;
import com.gpc.service.CheckInService;
import com.gpc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class CheckInController extends BasicController{
    @Autowired
    private CheckInService checkInService;
    //主业务
    @RequestMapping("/checkIn/{id}")
    public JsonResult checkIn(@PathVariable String id , String data) throws Exception {
        CheckIn checkIn = checkInService.insertRecord(id,data);
        System.out.println(data);
        return JsonResult.ok();
    }

    @RequestMapping("/checkInRecord/{id}")
    public JsonResult checkInRecord(@PathVariable String id) {
        AttendanceRecord attendanceRecord = checkInService.queryRecord(id);

        return JsonResult.ok(attendanceRecord);
    }
    //签到记录
    @RequestMapping("/checkInTime/{id}")
    public JsonResult checkInTime(@PathVariable String id) {

        HashMap<String , Object> paramMap = new HashMap<>();
        paramMap.put("id",id);
        paramMap.put("currentPage",0);
        paramMap.put("pageSize",pageSize);
        List<CheckIn> checkIn = checkInService.queryCheckInTime(paramMap);
        return JsonResult.ok(checkIn);
    }
    //获取本月签到数，用于签到页滑动栏
    @RequestMapping("/checkInDay/{id}")
    public JsonResult checkInDay(@PathVariable String id) {
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("id" , id);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        String s = year + "-" +month;
        paramMap.put("day",s);
        List<CheckIn> checkInList = checkInService.queryCheckInDay(paramMap);
        return JsonResult.ok(checkInList);
    }

    //判断当天是否签到
    @RequestMapping("/checkStatus/{id}")
    public JsonResult checkStatus (@PathVariable String id) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("id",id);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        String s = year + "-" +month + "-" + day;
        if(day < 10) {
            s = year + "-" +month + "-" + "0" + day;
        }
        map.put("day",s);
        CheckIn checkIn = checkInService.queryCheckStatus(map);
        return JsonResult.ok(checkIn);
    }


    //获取服务端时间
    @RequestMapping("/timer")
    public JsonResult timer() {
        Calendar c = Calendar.getInstance();
        int  day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);
        String result = "{" +
                "day:" + day +","+
                "month:" + month+"," +
                "year:" + year +
                "}";
        return JsonResult.ok(result);
    }
}

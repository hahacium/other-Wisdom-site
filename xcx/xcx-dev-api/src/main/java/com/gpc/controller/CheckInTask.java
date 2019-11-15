package com.gpc.controller;

import com.gpc.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 */
@Component
public class CheckInTask {
    @Autowired
    private CheckInService checkInService;
    @Scheduled(cron = "0/5 * * * * *")
    public void checkIn(){
        checkInService.task();
    }
}

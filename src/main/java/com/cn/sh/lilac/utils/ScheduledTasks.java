package com.cn.sh.lilac.utils;

import com.cn.sh.lilac.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {
    @Autowired
    private EmployeeService employeeService;
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    //0/5 每15秒 * 每一分钟 9-23 9点到23点 *每天 *每月
    //@Scheduled(cron="0/15 * 9-23 * * ?")
    //10秒 10分 1点 第一天 *每个月
    @Scheduled(cron="10 10 1 1 * ?")
    public void reportCurrentTime() {
        //call account upgrade
        employeeService.updateAccountMonthly();
        log.info("upgrade employee account ,The time is {}", dateFormat.format(new Date()));
    }
}

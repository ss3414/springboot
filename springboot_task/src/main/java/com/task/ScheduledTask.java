package com.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduledTask {

    /*
     * Scheduled+数据库
     * ①将包含Cron表达式的定时任务储存在数据库中
     * ②Scheduled定时任务每天查询定时任务，解析Cron表达式，判断是否当天执行
     * */
    @Scheduled(cron = "*/3 * * * * *") /* 每隔3秒触发1次 */
    public void test() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

}

package com;

import org.junit.jupiter.api.Test;
import org.quartz.CronScheduleBuilder;
import org.quartz.spi.MutableTrigger;

import java.text.SimpleDateFormat;

public class ApplicationTest {

    /* fixme 解析Cron表达式 */
    @Test
    public void test() {
        String cronExpression = "*/30 * * * * ?";
        MutableTrigger mutableTrigger = CronScheduleBuilder.cronSchedule(cronExpression).build();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(mutableTrigger.getStartTime()));
    }

}

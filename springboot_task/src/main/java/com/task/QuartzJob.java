package com.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mapper.QrtzJobDetailsMapper;
import com.model.QrtzJobDetails;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@DisallowConcurrentExecution /* 禁止并发执行 */
public class QuartzJob extends QuartzJobBean {

    /* SpringBoot2中采用extends QuartzJobBean而非implements Job的方式可以在Job中注入 */
    @Autowired
    private QrtzJobDetailsMapper qrtzJobDetailsMapper;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String jobName = jobExecutionContext.getJobDetail().getKey().getName(); /* 执行当前任务的jobName */

        List<QrtzJobDetails> qrtzSchedulerStateList = qrtzJobDetailsMapper.selectList(new QueryWrapper<>());

        LocalDateTime localDateTime = LocalDateTime.now();
        String time = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(time + ":QuartzJob");
    }

}

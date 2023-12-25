package com.demo.controller;

import com.demo.task.QuartzJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class QrtzController {

    private final Scheduler scheduler;

    /*
     * ①SchedulerFactoryBean可以注入，Scheduler初始化时获取
     * ②此处的Scheduler是Spring读取配置获取数据库中的调度器
     * */
    public QrtzController(@Autowired SchedulerFactoryBean schedulerFactoryBean) {
        scheduler = schedulerFactoryBean.getScheduler();
    }

    /*
     * 新建Quartz定时任务
     * ①qrtz_scheduler_state（调度器）
     * ②qrtz_job_details（任务）
     * ③qrtz_triggers（触发器）
     * ④qrtz_cron_triggers（Cron触发器）
     * ⑤qrtz_locks（锁，包含state和trigger）
     *
     * 可能出现的异常
     * ①jobName/cronExpression格式异常
     * ②jobName已存在
     * */
    @PostMapping("/create")
    public Map<String, Object> create(String jobName, String cronExpression) {
        try {
            JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class) /* 定时任务 */.withIdentity(jobName).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName).withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            e.printStackTrace();

            /* fixme 异常/校验 */
            String exceptionName = e.getClass().getName();
            switch (exceptionName) {
                case "org.quartz.ObjectAlreadyExistsException" -> System.out.println("jobName已存在");
                default -> System.out.println("其他异常");
            }
        }
        return new LinkedHashMap<>();
    }

    /* 修改 */
    @PostMapping("/update")
    public Map<String, Object> update(String jobName, String cronExpression) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName);
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName).withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
        scheduler.rescheduleJob(triggerKey, trigger);
        return new LinkedHashMap<>();
    }

    /* 立即执行 */
    @RequestMapping("/trigger")
    public Map<String, Object> trigger(String jobName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName);
        scheduler.triggerJob(jobKey);
        return new LinkedHashMap<>();
    }

    /* 暂停 */
    @RequestMapping("/pause")
    public Map<String, Object> pause(String jobName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName);
        scheduler.pauseJob(jobKey);
        return new LinkedHashMap<>();
    }

    /* 恢复 */
    @RequestMapping("/resume")
    public Map<String, Object> resume(String jobName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName);
        scheduler.resumeJob(jobKey);
        return new LinkedHashMap<>();
    }

    /* 删除 */
    @RequestMapping("/delete")
    public Map<String, Object> delete(String jobName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName);
        scheduler.deleteJob(jobKey);
        return new LinkedHashMap<>();
    }

}

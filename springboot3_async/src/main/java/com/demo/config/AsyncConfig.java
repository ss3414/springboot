package com.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Arrays;
import java.util.concurrent.Executor;

@Slf4j
@Configuration
@EnableAsync(proxyTargetClass = true)
public class AsyncConfig implements AsyncConfigurer {

    /* 自定义线程池 */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(2); /* 核心线程数 */
        taskExecutor.setMaxPoolSize(10); /* 最大线程数 */
        taskExecutor.setQueueCapacity(15); /* 队列大小 */
        taskExecutor.setThreadNamePrefix("custome-");
        taskExecutor.initialize();
        return taskExecutor;
    }

    /*
    * fixme 异常处理
    * 出错应当让数据库回滚
    * */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {
            throwable.printStackTrace();
            log.error("Exception:{} Method:{} Parameter:{}", throwable.getMessage(), method.getName(), Arrays.toString(objects));
        };
    }

}

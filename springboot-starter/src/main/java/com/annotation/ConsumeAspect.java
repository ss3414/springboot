package com.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class ConsumeAspect {

    private static final Logger logger = LoggerFactory.getLogger(ConsumeAspect.class);

    @Pointcut("@annotation(com.annotation.Consume)")
    public void consume() {
    }

    /* Spring AOP处理注解 */
    @Around(value = "consume()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        try {
            LocalDateTime begin = LocalDateTime.now();
            result = point.proceed(); /* 执行被拦截方法 */
            LocalDateTime end = LocalDateTime.now();
            Duration duration = Duration.between(begin, end);
            MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();
            Consume consume = method.getAnnotation(Consume.class);
            String unit = consume.unit();

            String name = signature.getName();
            String msg = "";
            if ("s".equals(unit)) {
                String beginTime = begin.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String endTime = end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String consumeTime = String.valueOf(duration.getSeconds());
                msg = String.format("%s %s~%s 耗时：%s秒", name, beginTime, endTime, consumeTime);
            } else if ("ms".equals(unit)) {
                String beginTime = begin.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
                String endTime = end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
                String consumeTime = String.valueOf(duration.toMillis());
                msg = String.format("%s %s~%s 耗时：%s毫秒", name, beginTime, endTime, consumeTime);
            }
            logger.info(msg);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

}

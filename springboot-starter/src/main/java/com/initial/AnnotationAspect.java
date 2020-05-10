package com.initial;

import com.annotation.Consume;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Order(1)
@Component
public class AnnotationAspect {

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
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(name);
            stringBuffer.append(" ");
            if ("s".equals(unit)) {
                stringBuffer.append(begin.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                stringBuffer.append("~");
                stringBuffer.append(end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                stringBuffer.append(" 耗时：");
                stringBuffer.append(duration.getSeconds());
                stringBuffer.append("秒");
            } else if ("ms".equals(unit)) {
                stringBuffer.append(begin.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
                stringBuffer.append("~");
                stringBuffer.append(end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
                stringBuffer.append(" 耗时：");
                stringBuffer.append(duration.toMillis());
                stringBuffer.append("毫秒");
            }
            System.out.println(stringBuffer.toString());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

}

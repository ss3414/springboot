package com.demo.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.UUID;

@Aspect
@Component
public class LogAutoAspect {

    @Pointcut("@annotation(com.demo.annotation.LogAuto)")
    public void logAuto() {
    }

    @Around(value = "logAuto()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MDC.put("traceId", UUID.randomUUID().toString());
        Object result = point.proceed();

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        LogAuto logAuto = method.getAnnotation(LogAuto.class);
        boolean outParamPrint = logAuto.outParamPrint();
        /* 如果outParamPrint为false，则在AOP中结束traceId的传递 */
        if (!outParamPrint) {
            MDC.clear();
        }
        return result;
    }

}

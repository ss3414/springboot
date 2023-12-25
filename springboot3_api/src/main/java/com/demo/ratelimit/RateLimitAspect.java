package com.demo.ratelimit;

import com.demo.exception.TestException;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class RateLimitAspect {

    private final ConcurrentHashMap<String, RateLimiter> EXISTED_RATE_LIMITERS = new ConcurrentHashMap<>();

    @Pointcut("@annotation(com.demo.ratelimit.RateLimit)")
    public void rateLimit() {
    }

    @Around("rateLimit()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        RateLimit annotation = AnnotationUtils.findAnnotation(method, RateLimit.class);

        /* 获取指定方法的限流器 */
        RateLimiter rateLimiter = EXISTED_RATE_LIMITERS.computeIfAbsent(method.getName(), k -> RateLimiter.create(annotation.limit()));
        if (rateLimiter != null && rateLimiter.tryAcquire()) {
            return point.proceed();
        } else {
            throw new TestException("too many requests");
        }
    }

}

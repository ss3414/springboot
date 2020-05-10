package com.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/*
 * ①@Aspect注解将Java类定义为切面类
 * ②切面类可以有顺序
 * */
@Aspect
@Order(1)
@Component
public class LogAspect {

    /* beginTime可能会有同步问题，所以使用ThreadLocal声明 */
    ThreadLocal<Long> beginTime = new ThreadLocal<>();

    /*
     * 切点
     * ①可以是AOP切点表达式/注解
     * ②此处是public修饰，返回值为任意类型，com.controller包下以Controller结尾的类的任意方法
     * */
    @Pointcut("execution(public * com.controller..*Controller.*(..))")
    public void point() {
    }

    /* 在切点执行前 */
    @Before("point()")
    public void before(JoinPoint joinPoint) {
        beginTime.set(System.currentTimeMillis());

        /* 切点切入的方法 */
        System.out.println("切点：" + joinPoint);

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println("SessionId：" + request.getSession().getId());
        System.out.println("路由：" + request.getRequestURL());
    }

    /* 在切点执行后 */
    @AfterReturning(returning = "returning", pointcut = "point()")
    public void afterReturning(Object returning) {
        System.out.println("返回：" + returning);
        System.out.println("耗时：" + (System.currentTimeMillis() - beginTime.get()) + "毫秒");
    }

}

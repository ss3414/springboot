package com.initial;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RunnerServlet {

    /*
     * @PostConstruct：相当于Servlet的init()方法，服务器加载Servlet时调用
     * @PreConstruct：相当于Servlet的destroy()方法，服务器卸载Servlet时调用
     * */
    @PostConstruct
    private void test() {
        System.out.println("RunnerServlet");
    }

}

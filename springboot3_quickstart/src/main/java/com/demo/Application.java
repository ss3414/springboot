package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * 1 @SpringBootApplication相当于@Configuration+@EnableAutoConfiguration+@ComponentScan
 * 2 职责
 * ①被@SpringBootApplication标注的类，可以用@Bean配置Bean
 * ②启动Spring上下文（Context）自动配置
 * ③扫描Starter组件配置类和被@Configuration标注的类（Starter组件默认配置+自定义配置）
 * */
@SpringBootApplication
public class Application {

    /*
     * 启动方式
     * ①使用Application启动
     * ②使用Maven启动（需要spring-boot-maven-plugin依赖）
     * （在POM.xml根目录执行命令mvn spring-boot:run）
     * ③使用java命令启动
     * （使用Maven或Gradle将项目打成jar包，用java命令执行jar包）
     * （java -jar springboot3_quickstart-1.0.jar）
     * */

    /*
     * 服务器裸机部署方式
     * ①Windows：用WinSW注册为服务
     * ②Linux：nohup java -jar springboot3_quickstart-1.0.jar &
     * */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

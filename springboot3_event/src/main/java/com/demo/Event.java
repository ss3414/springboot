package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@SpringBootApplication
public class Event {

    public static void main(String[] args) {
        SpringApplication.run(Event.class, args);
    }

    @Autowired
    private DemoEventPublisher demoEventPublisher;

    @RequestMapping("/")
    public Map index() {
        demoEventPublisher.publishDemoEvent("demo");
        return new LinkedHashMap();
    }

}

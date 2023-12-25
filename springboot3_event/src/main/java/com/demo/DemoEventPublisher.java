package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DemoEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishDemoEvent(String message) {
        DemoEvent demoEvent = new DemoEvent(this, message);
        applicationEventPublisher.publishEvent(demoEvent);
    }

}

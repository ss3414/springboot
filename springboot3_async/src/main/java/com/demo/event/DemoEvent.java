package com.demo.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class DemoEvent extends ApplicationEvent {

    private String message;

    public DemoEvent(String message) {
        super(message);
        this.message = message;
    }

}

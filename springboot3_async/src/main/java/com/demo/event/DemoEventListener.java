package com.demo.event;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DemoEventListener {

    @EventListener
    public void handleSync(DemoEvent event) {
        log.info(event.getMessage());
        log.info("handle event");
    }

    @Async
    @EventListener
    @SneakyThrows
    public void handleAsync(DemoEvent event) {
        log.info(event.getMessage());
        Thread.sleep(3000);
        log.info("handle event");
        throw new Exception("test exception");
    }

}

package com.demo;

import com.demo.websocket.WebSocket1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@SpringBootApplication
public class WebSocket {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(WebSocket.class, args);
        WebSocket1.setApplicationContext(applicationContext);
    }

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    @RequestMapping("/client")
    public ModelAndView client() {
        return new ModelAndView("/client");
    }

    @RequestMapping("/server")
    public ModelAndView server() {
        return new ModelAndView("/server");
    }

}

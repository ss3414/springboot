package com.demo;

import com.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@SpringBootApplication
public class Docker {

    public static void main(String[] args) {
        SpringApplication.run(Docker.class, args);
    }

    @Autowired
    private UserDao userDao;

    @RequestMapping("/")
    public Map index() {
        Map map = new LinkedHashMap();
        map.put("status", 1000);
        map.put("user", userDao.findById(1L));
        return map;
    }

}

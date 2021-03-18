package com.controller;

import com.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class IndexController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/")
    public Map index() {
        Map map = new HashMap();
        map.put("status", 1000);
        map.put("user", userDao.findById(1L));
        return map;
    }

}

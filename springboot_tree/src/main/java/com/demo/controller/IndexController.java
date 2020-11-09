package com.demo.controller;

import com.demo.dao.UserDao;
import com.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("")
public class IndexController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    @GetMapping("/user")
    public Map user() {
        Optional<User> result = userDao.findById(1L);
        User user = new User();
        if (result.isPresent()) {
            user = result.get();
        }
        Map map = new HashMap();
        map.put("result", user.toString());
        return map;
    }

}

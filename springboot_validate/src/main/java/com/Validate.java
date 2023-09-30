package com;

import com.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@SpringBootApplication
public class Validate {

    public static void main(String[] args) {
        SpringApplication.run(Validate.class, args);
    }

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    @RequestMapping("/form")
    public Map form(@Valid User user) {
        Map map = new LinkedHashMap();
        map.put("user", user);
        return map;
    }

}

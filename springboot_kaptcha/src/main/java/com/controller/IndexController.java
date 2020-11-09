package com.controller;

import com.baomidou.kaptcha.Kaptcha;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("")
public class IndexController {

    @Autowired
    private Kaptcha kaptcha;

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    @GetMapping("/render")
    public void render() {
        kaptcha.render();
    }

    @PostMapping("/valid")
    public void valid(@RequestParam String code) {
        try {
            kaptcha.validate(code, 900);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/img")
    public void img(HttpServletResponse response, String code) {
        try {
            File file = new File("C:/Users/Administrator/Desktop/test.jpg");
            FileInputStream fileInputStream = new FileInputStream(file);
            OutputStream outputStream = response.getOutputStream();
            IOUtils.write(IOUtils.toByteArray(fileInputStream), outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

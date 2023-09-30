package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Job {

    public static void main(String[] args) {
        SpringApplication.run(Job.class, args);
    }

    @RequestMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("Index", HttpStatus.OK);
    }

}

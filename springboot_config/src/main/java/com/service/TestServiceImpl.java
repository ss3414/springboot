package com.service;

import org.springframework.stereotype.Component;

@Component
public class TestServiceImpl implements TestService {

    @Override
    public void test() {
        System.out.println("TestServiceImpl");
    }

}

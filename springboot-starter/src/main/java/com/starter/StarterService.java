package com.starter;

import com.annotation.Consume;
import lombok.Data;

@Data
public class StarterService {

    private CustomProperties customProperties;

    public StarterService(CustomProperties customProperties) {
        this.customProperties = customProperties;
    }

    @Consume
    public void test() {
        System.out.println(customProperties.getFlag());
    }

}

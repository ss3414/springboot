package com.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
//@Component
@ConfigurationProperties("custom")
public class CustomProperties {

    private Boolean flag;

}

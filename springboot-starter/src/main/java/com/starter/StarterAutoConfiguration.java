package com.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CustomProperties.class)
@ConditionalOnClass({CustomProperties.class})
@ConditionalOnProperty(prefix = "custom", value = "flag", matchIfMissing = true) /* 当配置文件中配置了custom.flag时进行自动配置，反之使用matchIfMissing */
public class StarterAutoConfiguration {

    @Autowired
    private CustomProperties customProperties; /* 此处注入的CustomProperties与custom.flag+@ConditionalOnProperty对应？ */

    @Bean
    @ConditionalOnMissingBean(StarterService.class) /* 当容器中没有StarterService时，自动配置 */
    public StarterService starterService() {
        return new StarterService(customProperties);
    }

}

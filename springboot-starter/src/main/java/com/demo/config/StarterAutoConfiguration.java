package com.demo.config;

import com.demo.util.StarterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CustomProperties.class)
public class StarterAutoConfiguration {

    /*
     * ①@ConfigurationProperties+@EnableConfigurationProperties实例化CustomProperties
     * ②实例化后，Spring容器中拥有一个CustomProperties对象，可以被注入
     * */
    @Autowired
    private CustomProperties customProperties;

//    @Bean
//    @ConditionalOnMissingBean(StarterUtil.class) /* 当容器中没有StarterUtil时，自动配置 */
//    @ConditionalOnProperty(prefix = "custom", value = "flag") /* 当配置文件中配置了custom.flag时，自动配置 */
    public StarterUtil starterUtil() {
        return new StarterUtil(customProperties);
    }

}

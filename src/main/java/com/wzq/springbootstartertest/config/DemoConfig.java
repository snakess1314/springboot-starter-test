package com.wzq.springbootstartertest.config;

import com.wzq.springbootstartertest.properties.DemoProperties;
import com.wzq.springbootstartertest.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@EnableConfigurationProperties(DemoProperties.class)
@AutoConfigureBefore
@ConditionalOnProperty(
        prefix = "demo",
        name = "isopen",
        havingValue = "true"
)
public class DemoConfig {
    @Autowired
    private DemoProperties demoProperties;
    @Autowired
    private ApplicationContext context;
    @Bean(name = "demo",initMethod = "init")
    public DemoService demoService(){
        return new DemoService(demoProperties.getSayWhat(), demoProperties.getToWho());
    }
    @Bean
    public void show1(){
        System.out.println("我是先手");
    }
}

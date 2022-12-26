package com.wzq.springbootstartertest.config;

import com.wzq.springbootstartertest.service.DemoService;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter
public class DemoConfig2 {
    @Bean
    @ConditionalOnMissingBean(value = {DemoService.class})
    public void show(){
        System.out.println("存在这个对象才执行");
    }

}

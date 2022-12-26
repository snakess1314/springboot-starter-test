package com.wzq.springbootstartertest.controller;


import com.sun.istack.internal.NotNull;
import com.wzq.springbootstartertest.bean.Result;
import com.wzq.springbootstartertest.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoController {
    @Value("${custom.property}")
    private String name;
    @Resource(name = "demo")
    private DemoService demoService;
    @Autowired
    private Environment environment;

    @GetMapping("/say")
    public String sayWhat( ) {
        return demoService.say();
    }

    @GetMapping("/show")
    public Result<String> show() {
        if (true) {
            //throw new BizException("没有数据");
            throw new IllegalArgumentException("参数长度不是7位");
        }
        return Result.ok("zhangsan");
    }

    @GetMapping("/show1")
    public void show1( String name) {
        Binder binder = Binder.get(environment);
        int z=1/0;
        BindResult<Map<String, Object>> kafaka = binder.bind("kafaka", Bindable.mapOf(String.class, Object.class));
        Map<String, Object> hashMap = kafaka.get();
        System.out.println(hashMap);
    }
}

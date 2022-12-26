package com.wzq.springbootstartertest.config;

import com.wzq.springbootstartertest.properties.DemoProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class CustEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Resource
    private DemoProperties demoProperties;
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        System.out.println("初始话");
        Map<String, Object> source = new HashMap<>();
/*        try {
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "*.yaml");
            System.out.println(""+resources.length);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        source.put("configCenter.zookeeper.address", "192.168.100.100:2181");
        source.put("configCenter.redis.address", "192.168.100.100:6379");
        // ArrayList<NacosConfigProperties.Config> objects = new ArrayList<>();
        // objects.add(new NacosConfigProperties.Config("base-common.yml", "DEFAULT_GROUP", true));
        // source.put("spring.cloud.nacos.config.extension-configs", objects);
        MapPropertySource propertiesPropertySource = new MapPropertySource("configCenter", source);
        environment.getPropertySources().addLast(propertiesPropertySource);
    }

}

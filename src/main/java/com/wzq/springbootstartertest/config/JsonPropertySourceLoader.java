package com.wzq.springbootstartertest.config;


import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonPropertySourceLoader implements PropertySourceLoader {
    @Override
    public String[] getFileExtensions() {
        return new String[]{"json"};
    }

    @Override
    public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
        MapPropertySource mapPropertySource = new MapPropertySource(name, mapPropertySource(resource));
        return Collections.singletonList(mapPropertySource);
    }

    /**
     * Resource转Map
     *
     * @param resource
     * @return
     */
    private Map<String, Object> mapPropertySource(Resource resource) {

        Map<String, Object> result = new HashMap<>();
        // 获取json格式的Map
        Map<String, Object> fileMap = JSONObject.parseObject(readFile(resource),Map.class);
        // 组装嵌套json
        processorMap("", result, fileMap);
        return result;
    }
    private void processorMap(String prefix, Map<String, Object> result, Map<String, Object> fileMap) {
        if (prefix.length() > 0) {
            prefix += ".";
        }
        for (Map.Entry<String, Object> entrySet : fileMap.entrySet()) {
            if (entrySet.getValue() instanceof Map) {
                processorMap(prefix + entrySet.getKey(), result, (Map<String, Object>) entrySet.getValue());
            } else {
                result.put(prefix + entrySet.getKey(), entrySet.getValue());
            }
        }
    }
    /**
     * JSON格式
     *
     * @param resource
     * @return
     */
    private String readFile(Resource resource) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(resource.getFile());
            String str = "";
            byte[] readByte = new byte[1024];
            int length;
            while ((length = fileInputStream.read(readByte)) > 0) {
                str += new String(readByte, 0, length, "UTF-8");
            }

            return str;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}

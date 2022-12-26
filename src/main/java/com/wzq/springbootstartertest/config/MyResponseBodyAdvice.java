package com.wzq.springbootstartertest.config;

import com.alibaba.fastjson.JSON;
import com.wzq.springbootstartertest.bean.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        Result result;
        //获取返回值类型
        String returnClassType = methodParameter.getParameterType().getSimpleName();

        //如果返回值类型为void，则默认返回成功
        if("void".equals(returnClassType)){
            result = Result.ok();
        }else if ("Result".equals(returnClassType)){
            result = (Result) o;
        }else if ("String".equals(returnClassType)){
            //json的转换会出问题
            result = Result.ok((String) o);
            return JSON.toJSONString(result);
        }else {
           return o;
        }
        //一定要转换为String，否则会报错
        return result;
    }
}

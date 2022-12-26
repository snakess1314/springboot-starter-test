package com.wzq.springbootstartertest.config;

import com.wzq.springbootstartertest.bean.BizException;
import com.wzq.springbootstartertest.bean.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice

public class GlobalExceptionHandler {
    /**
     * 处理自定义的业务异常
     * @param e	异常对象
     * @param request	request
     * @return	错误结果
     */
    @ExceptionHandler(BizException.class)
    public Result bizExceptionHandler(BizException e, HttpServletRequest request) {
        System.out.println("发生业务异常！原因是: {}"+e.getMessage());
        return Result.fail(e.getCode(), e.getMessage());
    }
    // 拦截抛出的异常，@ResponseStatus：用来改变响应状态码
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public Result handlerThrowable(Throwable e, HttpServletRequest request) {
        System.out.println("发生未知异常！原因是: "+ e);
        Result error = Result.fail(3999, e.getMessage());
        return error;
    }

}

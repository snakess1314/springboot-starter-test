package com.wzq.springbootstartertest.bean;

public class Result<T> {
    /**
     * 错误码.
     */
    private Integer code = 0;

    /**
     * 提示信息.
     */
    private String msg;

    /**
     * 具体的内容.
     */
    private T data;

    public static Result ok() {
        return new Result();
    }

    public static Result ok(String object) {
        Result<String> result = new Result<>();
        result.setCode(0);
        result.setData(object);
        return result;
    }

    public static Result fail(Integer code, String message) {
        Result<String> result = new Result<>();
        result.setCode(code);
        result.setMsg(message);
        return result;

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

package com.wzq.springbootstartertest.service;

public class DemoService {
    public String sayWhat;
    public String toWho;
    public DemoService(String sayWhat, String toWho){
        this.sayWhat = sayWhat;
        this.toWho = toWho;
    }
    public String say(){
        return this.sayWhat + "!  " + toWho;
    }
    public void init(){
        System.out.println("初始化前方法init");
    }
}

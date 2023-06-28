package com.example.demo_3.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class testController {
    /*
    restful风格请求：
    原本样式：/user/id=1
    restful风格：/user/1
    GET , POST , PUT , DELETE
     */
    /*
      @GetMapping: 用于处理HTTP GET请求。通常用于获取数据。
    @PostMapping: 用于处理HTTP POST请求。通常用于提交数据以创建新的资源。
    @PutMapping: 用于处理HTTP PUT请求。通常用于更新现有资源的全部数据。
    @DeleteMapping: 用于处理HTTP DELETE请求。通常用于删除资源。
    @RequestMapping: 这是一种通用注解，可以处理所有类型的HTTP请求。
    */
    @RequestMapping("/hello")
    public String hello(){
        return "hefsefsgrgerlglo World 2e";
    }

    @PostMapping("/hello/post")
    public String hellopost(String name){
        return "helflo Worlfwd"+name;
    }
}

package com.example.demo_3.controller;


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
    // @GetMapping  获取注释
    //PostMapping   修改注释
    //PutMapping    新增注释
    //DeleteMapping   删除注释
    //RequestMapping   包括了以上四种注释，通常可以直接用RequestMapping 来代替。
    @RequestMapping("/Hello")
    public String hello(){

        return "hello World";
    }
}

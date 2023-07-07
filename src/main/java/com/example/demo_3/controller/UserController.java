package com.example.demo_3.controller;

import com.example.demo_3.req.UserLoginReq;
import com.example.demo_3.req.UserQueryReq;
import com.example.demo_3.req.UserResetPasswordReq;
import com.example.demo_3.req.UserSaveReq;
import com.example.demo_3.resp.CommonResp;
import com.example.demo_3.resp.PageResp;
import com.example.demo_3.resp.UserLoginResp;
import com.example.demo_3.resp.UserQueryResp;
import com.example.demo_3.service.UserService;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/user/list")
    public CommonResp list(@Valid  UserQueryReq req){
        CommonResp<PageResp<UserQueryResp>> resp = new CommonResp<>();

        PageResp<UserQueryResp> list =  userService.list(req);
        resp.setContent(list);
        return resp;
    }
    @PostMapping("/user/save")
    public CommonResp save(@Valid @RequestBody UserSaveReq req){
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        System.out.println(req.getPassword());
        CommonResp resp = new CommonResp<>();
        userService.save(req);
        return resp;
    }

    @PostMapping("/user/reset-password")
    public CommonResp resetPassword(@Valid @RequestBody UserResetPasswordReq req){
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        System.out.println(req.getPassword());
        CommonResp resp = new CommonResp<>();
        userService.resetPassword(req);
        return resp;
    }

    @DeleteMapping("/user/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp = new CommonResp<>();
        userService.delete(id);
        return resp;
    }

    @PostMapping("/login")
    public CommonResp login(@Valid @RequestBody UserLoginReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp<UserLoginResp> resp = new CommonResp<>();
        UserLoginResp userLoginResp = userService.login(req);

//        Long token = snowFlake.nextId();
//        LOG.info("生成单点登录token：{}，并放入redis中", token);
//        userLoginResp.setToken(token.toString());
//        redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(userLoginResp), 3600 * 24, TimeUnit.SECONDS);

        resp.setContent(userLoginResp);
        return resp;
    }
}

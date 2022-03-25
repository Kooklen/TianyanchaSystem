package com.qiyue.controller;

import com.qiyue.service.LoginService;
import com.qiyue.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LogoutController {

    @Autowired
    private LoginService loginService;

    //获取头部信息这样一个参数
    @GetMapping("/logout")
    public Result logout(@RequestHeader("token") String token){
        return loginService.logout(token);
    }
}
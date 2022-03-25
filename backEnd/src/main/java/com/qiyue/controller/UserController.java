package com.qiyue.controller;

import com.qiyue.service.SysUserService;
import com.qiyue.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/userinfo")
    public Result currentUser(@RequestHeader("token") String token){

        return sysUserService.findUserByToken(token);
    }
}
package com.qiyue.service;

import com.qiyue.dao.pojo.SysUser;
import com.qiyue.vo.Result;

public interface SysUserService {
    SysUser findUser(String account, String password);

    Result findUserByToken(String token);
}

package com.qiyue.service;

import com.qiyue.dao.pojo.SysUser;
import com.qiyue.vo.Result;
import com.qiyue.vo.params.LoginParam;

public interface LoginService {
    /**
     * 登录
     * @param loginParam
     * @return
     */
    Result login(LoginParam loginParam);

    SysUser checkToken(String token);

    Result logout(String token);
}

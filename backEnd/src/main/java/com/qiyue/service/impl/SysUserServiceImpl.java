package com.qiyue.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.qiyue.dao.mapper.SysUserMapper;
import com.qiyue.dao.pojo.SysUser;
import com.qiyue.service.LoginService;
import com.qiyue.service.SysUserService;
import com.qiyue.vo.ErrorCode;
import com.qiyue.vo.LoginUserVo;
import com.qiyue.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findUser(String userid, String password){
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserid,userid);
        queryWrapper.eq(SysUser::getPassword,password);
        queryWrapper.select(SysUser::getUserid,SysUser::getPassword,SysUser::getUsername,SysUser::getStarttime,SysUser::getEndtime,SysUser::isMonitorf,SysUser::isSearchfunction);
        queryWrapper.last("limit 1");
        return sysUserMapper.selectOne(queryWrapper);
    }


    @Autowired
    private LoginService loginService;
    @Override
    public Result findUserByToken(String token) {
        SysUser sysUser = loginService.checkToken(token);
        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setUserid(sysUser.getUserid());
        loginUserVo.setUsername(sysUser.getUsername());
        loginUserVo.setStarttime(sysUser.getStarttime());
        loginUserVo.setEndtime(sysUser.getEndtime());
        loginUserVo.setSearchfunction(sysUser.isSearchfunction());
        loginUserVo.setMonitorf(sysUser.isMonitorf());
        return Result.success(loginUserVo);

    }

    ;
}
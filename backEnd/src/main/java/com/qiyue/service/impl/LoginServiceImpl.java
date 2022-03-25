package com.qiyue.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.qiyue.dao.pojo.SysUser;
import com.qiyue.service.LoginService;
import com.qiyue.service.SysUserService;
import com.qiyue.utilis.JWTUtils;
import com.qiyue.vo.ErrorCode;
import com.qiyue.vo.Result;
import com.qiyue.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

//Spring 自动扫描组件// https://blog.csdn.net/u010002184/article/details/72870065
// @Component – 指示自动扫描组件。
//@Repository – 表示在持久层DAO组件。
//@Service – 表示在业务层服务组件。
//@Controller – 表示在表示层控制器组件。
@Service
public class LoginServiceImpl implements LoginService {

    //加密盐用于加密
//    private static final String slat = "mszlu!@#";


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public Result login(LoginParam loginParam) {
        /**
         * 1. 检查参数是否合法
         * 2. 根据用户名和密码去user表中查询 是否存在
         * 3. 如果不存在 登录失败
         * 4. 如果存在 ，使用jwt 生成token 返回给前端
         * 5. token放入redis当中，redis  token：user信息 设置过期时间
         *  (登录认证的时候 先认证token字符串是否合法，去redis认证是否存在)
         */
        String userid = loginParam.getUserid();
        String password = loginParam.getPassword();
        if (StringUtils.isBlank(userid) || StringUtils.isBlank(password)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }

        SysUser sysUser = sysUserService.findUser(userid,password);
        if (sysUser == null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }

        //登录成功，使用JWT生成token，返回token和redis中
        String token = JWTUtils.createToken(sysUser.getUserid());

        redisTemplate.opsForValue().set(token, JSON.toJSONString(sysUser, SerializerFeature.WRITE_MAP_NULL_FEATURES),7, TimeUnit.DAYS);
        return Result.success(token);
    }


    @Override
    public SysUser checkToken(String token) {
        //token为空返回null
        if(StringUtils.isBlank(token)){
            return null;
        }
        Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);
        //解析失败
        if(stringObjectMap ==null){
            return null;
        }
        //如果成功
        String userJson =  redisTemplate.opsForValue().get(token);
        if (StringUtils.isBlank(userJson)) {
            return null;
        }
        //解析回sysUser对象
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        return sysUser;
    }

    @Override
    public Result logout(String token) {
        //后端直接删除redis中的token
        redisTemplate.delete(token);
        return Result.success(null);
    }



}
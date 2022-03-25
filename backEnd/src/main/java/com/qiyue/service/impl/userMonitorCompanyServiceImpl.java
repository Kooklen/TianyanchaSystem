package com.qiyue.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qiyue.dao.mapper.userMonitorCompanyMapper;
import com.qiyue.dao.pojo.monitorCompany;
import com.qiyue.service.userMonitorCompanyService;
import com.qiyue.vo.params.umcParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userMonitorCompanyServiceImpl implements userMonitorCompanyService {

    @Autowired
    private userMonitorCompanyMapper mapper;

    @Override
    public List findCompany(umcParam umcparam) {
        LambdaQueryWrapper<monitorCompany> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(monitorCompany::getUserid,umcparam.getUserid());

        List list = mapper.selectList(queryWrapper);
        return list;
    }
}

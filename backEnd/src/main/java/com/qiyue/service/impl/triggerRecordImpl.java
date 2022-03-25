package com.qiyue.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qiyue.dao.mapper.triggerRecordMapper;
import com.qiyue.dao.pojo.triggerRecord;
import com.qiyue.service.triggerrecordService;
import com.qiyue.vo.params.triggerRecordParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class triggerRecordImpl implements triggerrecordService {
    @Autowired
    triggerRecordMapper mapper;

    @Override
    public List findCompany(triggerRecordParam param){
        LambdaQueryWrapper<triggerRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(triggerRecord::getUserid,param.getUserid());
        List list = mapper.selectList(queryWrapper);
        return list;
    }
}

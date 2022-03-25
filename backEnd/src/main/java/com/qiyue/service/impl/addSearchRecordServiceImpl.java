package com.qiyue.service.impl;

import com.qiyue.dao.mapper.addSearchRecordMapper;
import com.qiyue.dao.pojo.searchRecord;
import com.qiyue.service.addSearchRecordService;
import com.qiyue.vo.Result;
import com.qiyue.vo.params.searchRecordParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class addSearchRecordServiceImpl implements addSearchRecordService {

    @Autowired
    private addSearchRecordMapper mapper;


    @Override
    public Result addSearchRecord(searchRecord searchRecordParam) {
        searchRecord record = new searchRecord();
        record.setUserid(searchRecordParam.getUserid());
        record.setUsername(searchRecordParam.getUsername());
        record.setCompanyname(searchRecordParam.getCompanyname());
        record.setState("1");
        record.setSearchtime(new Date());
        mapper.insert(record);
        return Result.success(record);
    }
}

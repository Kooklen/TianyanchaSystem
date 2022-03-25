package com.qiyue.service.impl;


import com.qiyue.dao.mapper.addMonitorMapper;
import com.qiyue.dao.pojo.monitor;
import com.qiyue.service.addMonitorService;
import com.qiyue.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class addMonitorImpl implements addMonitorService {

    @Autowired
    private addMonitorMapper mapper;

    @Override
    public Result submitForm(monitor monitorparam) {
        monitor form = new monitor();
        form.setUserid(monitorparam.getUserid());
        form.setUsername(monitorparam.getUsername());
        form.setCompanyname(monitorparam.getCompanyname());
        form.setState(monitorparam.getState());
        form.setIsmonitor(true);
        form.setMonitortime(new Date());
        mapper.insert(form);
        return Result.success(form);
    }
}

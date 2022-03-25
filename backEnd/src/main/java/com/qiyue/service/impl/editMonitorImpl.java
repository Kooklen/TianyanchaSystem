package com.qiyue.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qiyue.dao.mapper.editMonitorMapper;
import com.qiyue.dao.pojo.editMonitor;
import com.qiyue.dao.pojo.monitorCompany;
import com.qiyue.service.editMonitorService;
import com.qiyue.vo.Result;
import com.qiyue.vo.params.editMonitorParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class editMonitorImpl implements editMonitorService {
    @Autowired
    private editMonitorMapper mapper;

    @Override
    public Result editForm(editMonitorParam editmonitorparam) {
        LambdaQueryWrapper<editMonitor> updateWrapper = new LambdaQueryWrapper<>();
        updateWrapper
                .eq(editMonitor::getUserid,editmonitorparam.getUserid())
                .eq(editMonitor::getCompanyname,editmonitorparam.getCompanyname());
        editMonitor editmonitor = new editMonitor();
        editmonitor.setCanceltime(new Date());
        editmonitor.setIsmonitor(false);
        return Result.success(mapper.update(editmonitor,updateWrapper));
//        monitorCompany monitorcompany = new monitorCompany();
//        monitorcompany.setIsmonitor(true);
//        return mapper.update(monitorcompany,updateWrapper);
//        return Result.success(updateWrapper);
    }
}

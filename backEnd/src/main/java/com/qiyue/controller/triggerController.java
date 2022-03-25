package com.qiyue.controller;

import com.qiyue.service.triggerrecordService;
import com.qiyue.vo.params.triggerRecordParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api")
public class triggerController {

    @Autowired
    private triggerrecordService service;

    @PostMapping("/triggerrecord")
    public List userMonitorCompany(@RequestBody triggerRecordParam param){

        return service.findCompany(param);

    }
}

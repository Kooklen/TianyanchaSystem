package com.qiyue.controller;

import com.qiyue.dao.pojo.searchRecord;
import com.qiyue.service.addSearchRecordService;
import com.qiyue.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class addSearchRecord {
    @Autowired
    private addSearchRecordService service;

    @PostMapping("/addSearchRecord")
    public Result addSearchRecord(@RequestBody searchRecord param){
        return service.addSearchRecord(param);
    }
}

package com.qiyue.controller;

import com.qiyue.dao.pojo.editMonitor;
import com.qiyue.service.editMonitorService;
import com.qiyue.vo.Result;
import com.qiyue.vo.params.editMonitorParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class editMonitorController {
    @Autowired
    private editMonitorService service;

    @PostMapping("/editMonitorController")
    public Result currentUser(@RequestBody editMonitorParam param){


        return service.editForm(param);
//        return null;
    }
}
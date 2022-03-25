package com.qiyue.controller;


import com.qiyue.dao.pojo.monitor;
import com.qiyue.service.addMonitorService;
import com.qiyue.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class addMonitorController {
    @Autowired
    private addMonitorService service;

    @PostMapping("/addMonitorController")
    public Result currentUser(@RequestBody monitor param){

        return service.submitForm(param);
    }
}

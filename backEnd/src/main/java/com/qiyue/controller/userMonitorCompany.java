package com.qiyue.controller;

import com.qiyue.service.userMonitorCompanyService;
import com.qiyue.vo.params.umcParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class userMonitorCompany {

        @Autowired
        private userMonitorCompanyService service;

        @PostMapping("/getmonitorcompany")
        public List userMonitorCompany(@RequestBody umcParam umcparam){

        return service.findCompany(umcparam);

    }
}


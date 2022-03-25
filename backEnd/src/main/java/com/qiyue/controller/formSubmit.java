package com.qiyue.controller;


import com.qiyue.service.formSubmitService;
import com.qiyue.vo.Result;
import com.qiyue.vo.params.formParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class formSubmit {
    @Autowired
    private formSubmitService formsubmitservice;

    @PostMapping("/enterpriseRegistration")
    public Result currentUser(@RequestBody formParam formparam){

        return formsubmitservice.submitForm(formparam);
    }
}

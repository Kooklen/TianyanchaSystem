package com.qiyue.service.impl;

import com.qiyue.dao.mapper.formSubmitMapper;
import com.qiyue.dao.pojo.SubmitForm;
import com.qiyue.service.formSubmitService;
import com.qiyue.vo.Result;
import com.qiyue.vo.params.formParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class formSubmitImpl implements formSubmitService {


    @Autowired
    private formSubmitMapper formmapper;

    @Override
    @Transactional
    public Result submitForm(formParam formparam) {
        SubmitForm form = new SubmitForm();
        form.setState(formparam.getState());
        form.setUserid(formparam.getUserid());
        form.setUsername(formparam.getUsername());
        form.setCompanyname(formparam.getCompanyname());
        form.setLegalpeople(formparam.getLegalpeople());
        form.setProvince(formparam.getProvince());
        form.setCity(formparam.getCity());
        form.setCode(formparam.getCreditcode());
        form.setCreatetime(new Date());
        form.setContactphone(formparam.getContactphone());
        formmapper.insert(form);
        return Result.success(form);
    }

}

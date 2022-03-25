package com.qiyue.service;

import com.qiyue.dao.pojo.SubmitForm;
import com.qiyue.vo.Result;
import com.qiyue.vo.params.formParam;

public interface formSubmitService {
    //提交反馈数据
    Result submitForm(formParam formparam);
}

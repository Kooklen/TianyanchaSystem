package com.qiyue.dao.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("work_order_management")
public class SubmitForm {
    private Long state;
    private String userid;
    private String username;
    private String companyname;
    private String legalpeople;
    private String province;
    private String city;
    private String code;
    private String contactphone;
    private Date createtime;
}

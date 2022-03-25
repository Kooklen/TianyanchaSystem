package com.qiyue.vo.params;

import lombok.Data;

import java.util.Date;

@Data
public class formParam {
    private Long state;
    private String userid;
    private String username;
    private String companyname;
    private String province;
    private String city;
    private String creditcode;
    private String contactphone;
    private String legalpeople;
    private Date createtime;
}

package com.qiyue.vo.params;

import lombok.Data;

import java.util.Date;

@Data

public class monitorParam {
    private String userid;
    private String username;
    private String companyid;
    private String companyname;
    private Date monitortime;
    private String state;
}

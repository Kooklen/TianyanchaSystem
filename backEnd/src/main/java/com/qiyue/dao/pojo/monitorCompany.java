package com.qiyue.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_monitor_company")
public class monitorCompany {
    private Long recordnumber;

    private String userid;

    private String username;

    private String companyname;

    private Boolean state;

    private Boolean ismonitor;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date monitortime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date canceltime;

}
package com.qiyue.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_monitor_company")
public class editMonitor {
    private String userid;
    private String companyname;
    private Boolean ismonitor;
    private Date canceltime;
}

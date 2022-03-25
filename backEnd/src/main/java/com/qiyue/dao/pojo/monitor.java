package com.qiyue.dao.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_monitor_company")
public class monitor {
    private String userid;
    private String username;
    private String companyid;
    private String companyname;
    private Date monitortime;
    private String state;
    private Boolean ismonitor;
}

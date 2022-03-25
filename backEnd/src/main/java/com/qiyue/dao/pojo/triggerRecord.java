package com.qiyue.dao.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("trigger_record")
public class triggerRecord {
    private String userid;

    private String companyid;

    private String companyname;

    private String pushway;

    private String address;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date triggertime;
}



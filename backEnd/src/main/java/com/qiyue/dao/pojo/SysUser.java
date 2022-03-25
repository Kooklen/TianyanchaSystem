package com.qiyue.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("user_serarch_recorddd")
public class SysUser {
    private Long id;

    private String userid;

    private String username;

    private String password;


    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date starttime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endtime;

    private boolean monitorf;

    private boolean searchfunction;


}


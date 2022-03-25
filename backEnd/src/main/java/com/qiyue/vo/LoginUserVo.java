package com.qiyue.vo;

import lombok.Data;

import java.util.Date;

@Data
public class LoginUserVo {
    //与页面交互

    private String userid;

    private String username;

    private Date starttime;

    private Date endtime;

    private boolean searchfunction;

    private boolean monitorf;
}

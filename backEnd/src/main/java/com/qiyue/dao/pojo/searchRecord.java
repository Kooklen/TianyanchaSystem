package com.qiyue.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.qiyue.vo.params.searchRecordParam;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_search_record")
public class searchRecord {
    private String userid;
    private String username;
//    private String companyid;
    private String companyname;
    private Date searchtime;
    private String state;
}


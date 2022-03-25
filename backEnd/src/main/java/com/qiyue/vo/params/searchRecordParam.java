package com.qiyue.vo.params;

//import com.sun.org.apache.xpath.internal.operations.Bool;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_search_record")
public class searchRecordParam {
    private String userid;
    private String username;
    private String companyname;
    private Date searchtime;
    private String state;
}

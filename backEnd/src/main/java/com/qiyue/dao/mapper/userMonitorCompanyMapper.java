package com.qiyue.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiyue.dao.pojo.monitorCompany;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface userMonitorCompanyMapper extends BaseMapper<monitorCompany> {
    @Select("select * from user_monitor_company where userid = #{userid}")
    monitorCompany selectByUserId(String userid);
}

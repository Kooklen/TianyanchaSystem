package com.qiyue.vo.params;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class umcParam {
   private Long recordnumber;

   private String userid;
//
//   private String username;
//
//   private String companyname;
//
//   private Boolean state;
//
//   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
//   private Date monitortime;
}

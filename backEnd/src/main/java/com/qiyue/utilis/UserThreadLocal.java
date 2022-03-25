package com.qiyue.utilis;

import com.qiyue.dao.pojo.SubmitForm;
import com.qiyue.dao.pojo.SysUser;

public class UserThreadLocal {

    private UserThreadLocal() {
    }

    //线程变量隔离
    private static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();

    public static void put(SysUser sysUser) {
        LOCAL.set(sysUser);
    }

    public static SysUser get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }


    private static final ThreadLocal<SubmitForm> LOCALs = new ThreadLocal<>();

    public static void put(SubmitForm form) {
        LOCALs.set(form);
    }

//    public static SubmitForm get() {return LOCALs.get();}
//    public static void remove() {
//        LOCALs.remove();}
//}
}


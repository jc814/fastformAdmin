package com.hzy.fastformadmin.Util;

import java.util.UUID;

public class StringUtil {
    public static String createUUID(){
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        uuid = uuid.replace("-", "");
        return uuid;
    }

    public static Boolean isNotEmpty(String str){
        if(str == null || "".equals(str)){
            return Boolean.FALSE;
        }else{
            return Boolean.TRUE;
        }
    }
}

package com.hzy.fastformadmin.Util;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {
    public static Map<String,Object> newMap(Object...args){
        Map<String,Object> result = new HashMap<>();
        if(args!=null&&args.length%2!=1){
            for(int j=0;j<args.length;j=j+2){
                result.put(args[j].toString(),args[j+1]);
            }
        }else{
            new RuntimeException("构建map失败，请检查参数");
        }
        return result;
    }
}

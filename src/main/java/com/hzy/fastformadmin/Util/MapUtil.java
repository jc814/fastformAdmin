package com.hzy.fastformadmin.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public static String getValue(Map map,String key){
        Object obj = map.get(key);
        if(obj == null){
            return "";
        }else {
            return obj==null?"":obj.toString();
        }
    }

    public static List<String> getValues(List<Map<String,Object>> mapList, String key){
        List<String> result = new ArrayList<>();
        for(Map<String,Object> map : mapList){
            result.add(getValue(map,key));
        }
        return result;
    }
}

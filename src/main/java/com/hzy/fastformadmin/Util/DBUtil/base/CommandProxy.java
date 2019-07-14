package com.hzy.fastformadmin.Util.DBUtil.base;

import com.hzy.fastformadmin.Util.DBUtil.annotation.TraceMethod;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class CommandProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (method.isAnnotationPresent(TraceMethod.class)) {
            Command command = (Command) o;
            System.out.println("--------------------------开始打印sql--------------------------");
            System.out.println("SQL语句: "+command.getSQLText());
            int i = 1;
            command.getBacthSQLParams().forEach(arr->{
                System.out.print("参数: [");
                Arrays.asList(arr).forEach(obj -> {
                    System.out.print(obj+",");
                });
                System.out.println("]");
            });
            for(Object obj : command.getSQLParams()){
                System.out.println("第"+i+"个参数: "+obj.toString());
                i++;
            }
            System.out.println("--------------------------结束打印sql--------------------------");
        }
        return  methodProxy.invokeSuper(o,objects);
    }
}

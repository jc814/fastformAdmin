package com.hzy.fastformadmin.Util.DBUtil.annotation;
import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TableName {
    public String value();
}

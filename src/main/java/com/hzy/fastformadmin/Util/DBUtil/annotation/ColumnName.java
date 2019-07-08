package com.hzy.fastformadmin.Util.DBUtil.annotation;
import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnName {
    public String value();
}

package com.hzy.fastformadmin.Util.DBUtil.annotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TraceMethod {

}

package com.bjzhang.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR})
//在运行时进行解析
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MethodInfo {
    String name() default "annotationTest";
    int id() default 0;
    Class<Long> gid();
}

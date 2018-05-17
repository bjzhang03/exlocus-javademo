package com.bjzhang.annotation;


import java.util.HashMap;
import java.util.Map;

//类成员注解
@MethodInfo(name = "type", gid = Long.class)
public class UserAnnotation {

    @MethodInfo(name = "param", id = 1, gid = Long.class) //类成员注解
    private Integer age;

    @MethodInfo(name = "construct", id = 2, gid = Long.class)//构造方法注解
    public UserAnnotation() {

    }

    @MethodInfo(name = "public method", id = 3, gid = Long.class) //类方法注解
    public void a() {
        Map<String, String> m = new HashMap<String, String>(0);
    }

    @MethodInfo(name = "protected method", id = 4, gid = Long.class) //类方法注解
    protected void b() {
        Map<String, String> m = new HashMap<String, String>(0);
    }

    @MethodInfo(name = "private method", id = 5, gid = Long.class) //类方法注解
    private void c() {
        Map<String, String> m = new HashMap<String, String>(0);
    }

    public void b(Integer a) {

    }
}

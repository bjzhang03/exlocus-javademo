package com.bjzhang.reflect;

        import java.lang.reflect.InvocationTargetException;
        import java.lang.reflect.Method;

public class Demo {
    public static void main(String[] args) {
        //1 获取字节码对象
        Class clazz = Person.class;
        try {
            //2 获取Method对象
            Method method = clazz.getDeclaredMethod("test", String.class);

            //3 通过字节码对象创建一个Object
            Object obj = clazz.newInstance();

            //4 通过对象调用方法
            method.invoke(obj, "hello world");


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

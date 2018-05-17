package com.bjzhang.reflect;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.*;
import java.util.HashMap;

public class Reflect {
    public static void getAllConstructor(String className) {
        try {
            Class<?> aClass = Class.forName(className);
            Constructor<?>[] constructors = aClass.getConstructors();
            System.out.println(JSON.toJSONString(constructors));
            Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
            System.out.println(JSON.toJSONString(declaredConstructors));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static Constructor getSpecifyConstructor(String className, Class<?>... clzs) {
        try {
            Class<?> aClass = Class.forName(className);
            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(clzs);
            System.out.println(JSON.toJSONString(declaredConstructor));
            declaredConstructor.setAccessible(true);
            return declaredConstructor;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void getFileds(String className) {
        try {
            Class<?> aClass = Class.forName(className);
            Field[] fields = aClass.getFields();
            System.out.println(JSON.toJSONString(fields));

            Field[] declaredFields = aClass.getDeclaredFields();
            System.out.println(JSON.toJSONString(declaredFields));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Field getSpecifyFiled(String className, String filedName) {
        Object o = null;
        try {
            Class<?> aClass = Class.forName(className);
            Field declaredField = aClass.getDeclaredField(filedName);
            declaredField.setAccessible(true);
            return declaredField;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void getMethods(String className) {
        try {
            Class<?> aClass = Class.forName(className);
            Method[] declaredMethods = aClass.getDeclaredMethods();
            System.out.println(JSON.toJSONString(declaredMethods));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Method getSpecifyMethod(String className, String methodName, Class<?>... clzs) {
        try {
            Class<?> aClass = Class.forName(className);
            Method declaredMethod = aClass.getDeclaredMethod(methodName, clzs);
            declaredMethod.setAccessible(true);
            System.out.println(JSON.toJSONString(declaredMethod));
            return declaredMethod;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }


}

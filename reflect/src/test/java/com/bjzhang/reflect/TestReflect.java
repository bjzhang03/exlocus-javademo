package com.bjzhang.reflect;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.lang.reflect.*;
import java.util.HashMap;

public class TestReflect {
    @Test
    public void testGetAllConstructor() {
        Reflect.getAllConstructor("com.bjzhang.reflect.Person");
    }

    @Test
    public void testGetSpecifyConstructor() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class[] cArg = new Class[0];
        Constructor method = Reflect.getSpecifyConstructor("com.bjzhang.reflect.Person", cArg);
        method.newInstance();
    }

    @Test
    public void testGetFileds() {
        Reflect.getFileds("com.bjzhang.reflect.Person");
    }

    @Test
    public void testGetSpecifyFileds() {
        System.out.println(JSON.toJSONString(
                Reflect.getSpecifyFiled("com.bjzhang.reflect.Person", "name")));
    }

    @Test
    public void testGetMethods() {
        Reflect.getMethods("com.bjzhang.reflect.Person");
    }

    @Test
    public void testGetSpecifyMethods() {
        Class[] cArg = new Class[1];
        cArg[0] = String.class;
        Reflect.getSpecifyMethod("com.bjzhang.reflect.Person", "test", cArg);
    }

    /**
     * 利用反射操作数组
     * 1 利用反射修改数组中的元素
     * 2 利用反射获取数组中的每个元素
     */
    @Test
    public void testArrayClass() {
        String[] strArray = new String[]{"5", "7", "暑期", "美女", "女生", "女神"};
        Array.set(strArray, 0, "帅哥");
        Class clazz = strArray.getClass();
        if (clazz.isArray()) {
            int length = Array.getLength(strArray);
            for (int i = 0; i < length; i++) {
                Object object = Array.get(strArray, i);
                String className = object.getClass().getName();
                System.out.println("--- object --- " + object + ",className=" + className);
            }
        }
    }

    @Test
    public void getGenericType() {
        try {
            Method method = Reflect.getSpecifyMethod("com.bjzhang.reflect.Person", "getGenericHelper", HashMap.class);
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            // 检验是否为空
            if (null == genericParameterTypes || genericParameterTypes.length < 1) {
                return;
            }
            // 取 getGenericHelper 方法的第一个参数
            ParameterizedType parameterizedType = (ParameterizedType) genericParameterTypes[0];
            Type rawType = parameterizedType.getRawType();
            System.out.println("--- rawType --- " + rawType);
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (actualTypeArguments == genericParameterTypes || actualTypeArguments.length < 1) {
                return;
            }
            //  打印出每一个类型
            for (int i = 0; i < actualTypeArguments.length; i++) {
                Type type = actualTypeArguments[i];
                System.out.println("--- type --- " + type);
            }
        } catch (Exception e) {

        }

    }
}

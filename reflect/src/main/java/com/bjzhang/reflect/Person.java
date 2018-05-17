package com.bjzhang.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;

public class Person {

    public String country;
    public String city;
    private String name;
    private String province;
    private Integer height;
    private Integer age;

    public Person() {
        System.out.println("default");
    }

    private Person(String country, String city, String name) {
        this.country = country;
        this.city = city;
        this.name = name;
    }

    public Person(String country, Integer age) {
        this.country = country;
        this.age = age;
    }

    private String getMobile(String number) {
        String mobile = "010-110" + "-" + number;
        return mobile;
    }

    public static void getGenericHelper(HashMap<String, Integer> hashMap) {
    }


    private void setCountry(String country) {
        this.country = country;

    }

    public Class getGenericType() {
        try {
            HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
            Method method = getClass().getDeclaredMethod("getGenericHelper", HashMap.class);
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            if (null == genericParameterTypes || genericParameterTypes.length < 1) {
                return null;
            }

            ParameterizedType parameterizedType = (ParameterizedType) genericParameterTypes[0];
            Type rawType = parameterizedType.getRawType();
            System.out.println("----> rawType=" + rawType);
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (actualTypeArguments == genericParameterTypes || actualTypeArguments.length < 1) {
                return null;
            }

            for (int i = 0; i < actualTypeArguments.length; i++) {
                Type type = actualTypeArguments[i];
                System.out.println("----> type=" + type);
            }
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public String toString() {
        return "Person{" + "country='" + country + '\'' + ", city='" + city + '\'' + ", name='"
                       + name + '\'' + ", province='" + province + '\'' + ", height=" + height + '}';
    }

    public void test(String s) {
        System.out.println(s);
    }
}

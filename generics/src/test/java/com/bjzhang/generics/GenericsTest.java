package com.bjzhang.generics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenericsTest {
    Logger logger = Logger.getLogger("com.bjzhang.generics.GenericsTest");

    @Test
    public void genericsTest1() {
        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();

        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();

        if (classStringArrayList.equals(classIntegerArrayList)) {
            System.out.println("泛型测试 类型相同");
        }
    }

    @Test
    public void genericsTest2() {
        //泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
        //传入的实参类型需与泛型的类型参数类型相同，即为Integer.
        Generic<Integer> genericInteger = new Generic<Integer>(123456);

        //传入的实参类型需与泛型的类型参数类型相同，即为String.
        Generic<String> genericString = new Generic<String>("key_vlaue");

        logger.info(genericInteger.getKey().toString());
        logger.info(genericString.getKey());
    }

    @Test
    public void genericsTest3() {
        Generic generic = new Generic("111111");
        Generic generic1 = new Generic(4444);
        Generic generic2 = new Generic(55.55);
        Generic generic3 = new Generic(false);
        System.out.print("泛型测试,key is ");
        System.out.println(generic.getKey());
        System.out.print("泛型测试,key is ");
        System.out.println(generic1.getKey());
        System.out.print("泛型测试,key is ");
        System.out.println(generic2.getKey());
        System.out.print("泛型测试,key is ");
        System.out.println(generic3.getKey());
    }

    public void showKeyValue(Generic<Number> obj){
        System.out.print("泛型测试 key value is ");
        System.out.println(obj.getKey());
    }

    @Test
    public void genericsTest4(){
        Generic<Integer> gInteger = new Generic<Integer>(123);
        Generic<Number> gNumber = new Generic<Number>(456);

        showKeyValue(gNumber);
    }

    public void showKeyValue1(Generic<?> obj){
        System.out.print("泛型测试 key value is ");
        System.out.println(obj.getKey());
    }

    @Test
    public void genericsTest5(){
        Generic<Integer> gInteger = new Generic<Integer>(123);
        Generic<Number> gNumber = new Generic<Number>(456);

        showKeyValue1(gNumber);
        showKeyValue1(gInteger);
    }

    @Test
    public void genericsTest6(){
        GenericFruit tmp=new GenericFruit();
        tmp.testGenerics();
    }
}

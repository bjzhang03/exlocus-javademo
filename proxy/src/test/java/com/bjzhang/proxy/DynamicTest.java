package com.bjzhang.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DynamicTest {
    @Test
    public void testDynamic() {
        MaoTaiJiu maotaijiu = new MaoTaiJiu();
        InvocationHandler jingxiao1 = new GuiTaiA(maotaijiu);
        SellWine dynamicProxy = (SellWine) Proxy.newProxyInstance(MaoTaiJiu.class.getClassLoader(),
                MaoTaiJiu.class.getInterfaces(), jingxiao1);
        dynamicProxy.maiJiu();
    }

    @Test
    public void testSellWine() {
        MaoTaiJiu maotaijiu = new MaoTaiJiu();
        WuLiangYe wu = new WuLiangYe();

        InvocationHandler jingxiao1 = new GuiTaiA(maotaijiu);
        InvocationHandler jingxiao2 = new GuiTaiA(wu);

        SellWine dynamicProxy = (SellWine) Proxy.newProxyInstance(MaoTaiJiu.class.getClassLoader(),
                MaoTaiJiu.class.getInterfaces(), jingxiao1);
        SellWine dynamicProxy1 = (SellWine) Proxy.newProxyInstance(MaoTaiJiu.class.getClassLoader(),
                MaoTaiJiu.class.getInterfaces(), jingxiao2);

        dynamicProxy.maiJiu();
        dynamicProxy1.maiJiu();
    }

    @Test
    public void testMarket(){
        MaoTaiJiu maotaijiu = new MaoTaiJiu();
        WuLiangYe wu = new WuLiangYe();
        FuRongWang fu = new FuRongWang();

        InvocationHandler jingxiao1 = new GuiTaiA(maotaijiu);
        InvocationHandler jingxiao2 = new GuiTaiA(wu);
        InvocationHandler jingxiao3 = new GuiTaiA(fu);

        SellWine dynamicProxy = (SellWine) Proxy.newProxyInstance(MaoTaiJiu.class.getClassLoader(),
                MaoTaiJiu.class.getInterfaces(), jingxiao1);
        SellWine dynamicProxy1 = (SellWine) Proxy.newProxyInstance(WuLiangYe.class.getClassLoader(),
                WuLiangYe.class.getInterfaces(), jingxiao2);
        dynamicProxy.maiJiu();
        dynamicProxy1.maiJiu();

        SellCigarette dynamicProxy2 = (SellCigarette) Proxy.newProxyInstance(FuRongWang.class.getClassLoader(),
                FuRongWang.class.getInterfaces(), jingxiao3);
        dynamicProxy2.sell();

        System.out.println("dynamicProxy1 class name:"+dynamicProxy1.getClass().getName());
    }
}

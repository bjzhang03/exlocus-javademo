package com.bjzhang.proxy;

public class FuRongWang implements SellCigarette {
    @Override
    public void sell() {
        System.out.println("售卖的是正宗的芙蓉王，可以扫描条形码查证。");
    }
}

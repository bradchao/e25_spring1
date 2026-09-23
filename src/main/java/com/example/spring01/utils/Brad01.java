package com.example.spring01.utils;

import org.springframework.stereotype.Component;
/*
    IoC => 控制反轉 => Bean => DI 注入
    => @Component => 類別
    => @Bean => 方法{寫東西} => return => Bean
 */
@Component
public class Brad01 {
    public Brad01(){
        System.out.println("Brad01()");
    }
    public Brad01(int a){
        System.out.println("Brad01(int)");
    }

    public void test1(){
        System.out.println("Brad01:test1()");
    }
}

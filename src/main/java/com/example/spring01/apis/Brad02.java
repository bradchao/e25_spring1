package com.example.spring01.apis;

import com.example.spring01.utils.Bike;
import com.example.spring01.utils.Bike1;
import com.example.spring01.utils.Brad01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     // => Bean => Component => IoC
@RequestMapping("/member")
public class Brad02 {
    @Autowired
    private Brad01 brad01;

    @Autowired
    private Brad01 brad02;

    @Autowired
    private Bike bike1; // = new Bike1()

    @Autowired
    private Bike bike2; // = new Bike2()

    @Qualifier("bike2")
    @Autowired
    private Bike bike3;

    @Autowired
    private Bike1 myBike;


    public Brad02(){System.out.println("Brad02()");}

    @RequestMapping("/iii01")
    public void test1(){
        System.out.println("test1()");
        brad01.test1();
        brad02.test1();
    }

    @RequestMapping("/iii02")
    public String test2(){
        return "<h1>Brad</h1>";
    }

    @RequestMapping("/test3")
    public void test3(){
        bike1.upSpeed();
        bike1.downSpeed();
    }

    @RequestMapping("/test4")
    public void test4(){
        bike2.upSpeed();
        bike2.downSpeed();
    }

    @RequestMapping("/test5")
    public void test5(){
        bike3.upSpeed();
        bike3.downSpeed();
    }

    @RequestMapping("/test6")
    public void test6(){
        myBike.upSpeed();
        myBike.downSpeed();
    }

}

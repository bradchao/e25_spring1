package com.example.spring01.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     // => Bean => Component => IoC
@RequestMapping("/member")
public class Brad02 {
    @Autowired
    private Brad01 brad01;

    @Autowired
    private Brad01 brad02;

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


}

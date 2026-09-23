package com.example.spring01.config;

import com.example.spring01.dto.Member;
import com.example.spring01.utils.User;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {
    public MyConfig(){System.out.println("MyConfig()");}

    @Bean
    public RestTemplate test1(){
        System.out.print("MyConfig:test1()");
        return new RestTemplate();
    }

    @Bean
    public Member test2(){
        Member member = new Member();
        member.setId(0L);
        member.setPasswd(null);
        return member;
    }

}

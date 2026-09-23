package com.example.spring01.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/brad06")
public class Brad06 {

    @Autowired
    private RestTemplate restTemplate;

//    private RestTemplate restTemplate;
//    public Brad06(){
//        restTemplate = new RestTemplate();
//    }

    @RequestMapping("/test1")
    public void test1(){
        String url = "https://data.moa.gov.tw/Service/OpenData/ODwsv/ODwsvAgriculturalProduce.aspx";
        String json = restTemplate.getForObject(url, String.class);
        System.out.print(json);

    }

}

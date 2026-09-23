package com.example.spring01.apis;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brad03")
public class Brad03 {

    @RequestMapping("/calc")
    public String calc(@RequestParam(required = false, defaultValue = "0") String x,
                       @RequestParam(required = false, defaultValue = "0") String y){
        System.out.printf("x = %s; y = %s\n", x, y);

        try {
            return String.valueOf(Integer.parseInt(x) + Integer.parseInt(y));
        }catch (NumberFormatException e){
            return "ERROR";
        }

    }
}

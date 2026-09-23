package com.example.spring01.apis;

import com.example.spring01.utils.User;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/test1")
    public void test1(@RequestBody User user){
        //System.out.println("OK");
        System.out.printf("%d:%s:%s:%d\n",
                    user.getId(),
                    user.getName(),
                    user.getGender()?"male":"female",
                    user.getAge());
    }

    @RequestMapping("/test2/{name}/{id}")
    public void test2(@PathVariable String name, @PathVariable String id){
        System.out.printf("%s:%s\n", name, id);
    }

    @RequestMapping("/test3")
    public void test3(@RequestHeader(name = "Content-Type") String contentType,
                      @RequestHeader String xx){
        System.out.printf("%s:%s\n", contentType, xx);
    }

    @RequestMapping("/test4/{name}/{id}")
    public void test4(
        @RequestParam(name="x", required = false, defaultValue = "0") String xx,
        @RequestParam(name="y", required = false, defaultValue = "0") String yy,
        @RequestBody User user,
        @PathVariable String name,
        @PathVariable String id,
        @RequestHeader(name = "Content-Type") String contentType,
        @RequestHeader String kk
    ){
        System.out.printf("%s:%s\n", xx, yy);
        System.out.printf("%s:%s\n", user.getName(), user.getAge());
        System.out.printf("%s:%s\n", name, id);
        System.out.printf("%s:%s\n", contentType, kk);
    }


}

package com.example.spring01.apis;

import com.example.spring01.utils.Member;
import com.example.spring01.utils.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/member")
@RestController
public class Brad04 {

    @PostMapping("")
    public void register(@RequestBody @Validated Member member){
        System.out.println("register()");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        System.out.println("delete()");
    }

    @PutMapping("")
    public void update(@RequestBody @Validated Member member){
        System.out.println("update()");
    }

    @GetMapping("")
    public void findAll(){
        System.out.println("findAll()");
    }

    @GetMapping("/{id}")
    public void findById(@PathVariable String id){
        System.out.println("findById()");
    }

    @GetMapping("/{name}/{gender}")
    public void findByNameAndGender(@PathVariable String name,
                                    @PathVariable String gender){
        System.out.println("findByNameAndGender()");
    }


}

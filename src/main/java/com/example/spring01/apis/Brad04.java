package com.example.spring01.apis;

import com.example.spring01.utils.User;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/member")
@RestController
public class Brad04 {

    @PostMapping("")
    public void register(@RequestBody User user){
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){

    }

    @PutMapping("")
    public void update(@RequestBody User user){

    }

    @GetMapping("")
    public void findAll(){

    }

    @GetMapping("/{id}")
    public void findById(@PathVariable String id){

    }

    @GetMapping("/{name}/{gender}")
    public void findByNameAndGender(@PathVariable String name,
                                    @PathVariable String gender){

    }


}

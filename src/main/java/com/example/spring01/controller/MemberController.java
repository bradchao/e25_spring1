package com.example.spring01.controller;

import com.example.spring01.entity.Member;
import com.example.spring01.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService service;

    /*
        request: account=?
        response: true/false
     */
    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkAccount(@RequestParam String account){
        boolean isExist = service.checkAccount(account);
        System.out.print(isExist?"Yes":"No");
        return ResponseEntity.ok(isExist);
    }

    /*
        request: Member {...}
        response: {"success": true/false}
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String,Boolean>> register(@RequestBody Member member){
        boolean isOK = service.register(member);
        Map<String,Boolean> map = Map.of("success", isOK);
        return ResponseEntity.ok(map);
    }



}

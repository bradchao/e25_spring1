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
        //Map<String,Boolean> map = Map.of("isExist", isExist);
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

    /*
        request: {account: xxx, passwd: xxx}
        response: {"success": true/false}
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String,Boolean>> login(@RequestBody Map<String, String> body){
        String account = body.get("account");
        String passwd = body.get("passwd");

        boolean isSuccess = service.login(account, passwd);
        Map<String,Boolean> map = Map.of("success", isSuccess);
        return ResponseEntity.ok(map);
    }




}

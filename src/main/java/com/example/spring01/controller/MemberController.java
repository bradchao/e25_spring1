package com.example.spring01.controller;

import com.example.spring01.entity.Member;
import com.example.spring01.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

        //boolean isSuccess = service.login(account, passwd);
        boolean isSuccess = service.loginV2(account, passwd);

        Map<String,Boolean> map = Map.of("success", isSuccess);
        return ResponseEntity.ok(map);
    }

    @PostMapping("/loginV2")
    public ResponseEntity<Map<String,Boolean>> login(
            @RequestBody Map<String, String> body,
            HttpSession session
            ){
        String account = body.get("account");
        String passwd = body.get("passwd");

        Member member = service.loginV3(account, passwd);
        Map<String,Boolean> map;
        if (member != null){
            session.setAttribute("member", member);
            map = Map.of("success", true);
        }else{
            session.invalidate();
            map = Map.of("success", false);
        }
        return ResponseEntity.ok(map);
    }

    @GetMapping("/logout")
    public void logout(HttpSession session){
        session.invalidate();
    }

    @Value("${company.name}")
    private String companyName;

    @Value("${company.tel}")
    private String companyTel;

    @PostMapping("/status")
    public ResponseEntity<Map<String,Object>> status(HttpSession session){
        Object member = session.getAttribute("member");

        Map<String,Object> map = new HashMap<>();
        map.put("success", member != null);
        map.put("member", member);
        map.put("companyName",companyName);
        map.put("companyTel",companyTel);


        return ResponseEntity.ok(map);
    }

}

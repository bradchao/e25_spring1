package com.example.spring01.controller;

import com.example.spring01.config.ReadConfig;
import com.example.spring01.dto.Base64Upload;
import com.example.spring01.dto.MemberForm;
import com.example.spring01.entity.Member;
import com.example.spring01.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    @PostMapping("/{id}")
    public void test1(@PathVariable Long id,
                      @RequestParam MultipartFile upload){
        try {
            byte[] bytes = upload.getBytes();
            String sql = """
                    UPDATE member
                    SET icon = :icon
                    WHERE id = :id
                    """;
            Map<String, Object> args = new HashMap<>();
            args.put("icon", bytes);
            args.put("id", id);
            int n = jdbc.update(sql, args);
            System.out.print(n > 0);


        } catch (IOException e) {
            throw new RuntimeException(e);  // 500
        }
    }

    @Autowired
    private ReadConfig readConfig;

    @PostMapping("/test2")
    public void test2(@ModelAttribute MemberForm memberForm){
        System.out.println(memberForm.getAccount());
        System.out.println(memberForm.getFiles().size());
        System.out.println(readConfig.getUploadDir());

        File here = new File(".");

        List<MultipartFile> files = memberForm.getFiles();
        for (MultipartFile file: files){
            if (!file.isEmpty()){
                try {
                    String target = here.getAbsolutePath() +  "/" + readConfig.getUploadDir()  +
                            memberForm.getAccount() + "_" +
                            file.getOriginalFilename();
                    System.out.println(target);
                    file.transferTo(new File(target));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    @PostMapping("/test3")
    public ResponseEntity<String> test3(@RequestBody Base64Upload upload){
        System.out.println(upload.getFileName());
        System.out.println(upload.getContentType());
        System.out.println(upload.getBase64());

        /*
            save table: 1. String : upload.getBase64()
                        2. blob: fileBytes => XX
            save File: fileBytes
         */

        byte[] fileBytes = Base64.getDecoder().decode(upload.getBase64());
        Path uploadDir = Path.of(readConfig.getUploadDir());
        Path filePath = uploadDir.resolve(upload.getFileName());
        try {
            Files.write(filePath, fileBytes);
            return ResponseEntity.ok("Upload Success");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Upload Failure");
        }




    }




}

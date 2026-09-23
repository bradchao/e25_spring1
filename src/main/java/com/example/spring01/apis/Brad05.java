package com.example.spring01.apis;

import com.example.spring01.dto.Member;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/brad05")
public class Brad05 {
    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    @RequestMapping("/test1")
    public void test1(){
        String sql = """
                INSERT INTO cust
                    (cname, tel, birthday)
                VALUES
                    (:cname, :tel, :birthday)
                """;
        HashMap<String,String> args = new HashMap<>();
        args.put("cname", "john");
        args.put("tel", "333");
        args.put("birthday", "1999-07-09");

        int n = jdbc.update(sql, args);
        System.out.printf("%d", n);
    }

    @PostMapping("/test2")
    public void test2(@RequestBody Member member){
        String sql = """
                INSERT INTO member
                    (account,passwd, name)
                VALUES
                    (:acc, :pass, :name)
                """;
        HashMap<String,String> args = new HashMap<>();
        args.put("acc", member.getAccount());
        args.put("pass", BCrypt.hashpw(member.getPasswd(), BCrypt.gensalt()));
        args.put("name", member.getName());

        int n = jdbc.update(sql, args);
        System.out.printf("%s\n", (n>0?"Success":"Failure"));
    }

    @PostMapping("/test3")
    public void test3(@RequestBody Member member){
        String sql = """
                INSERT INTO member
                    (account,passwd, name)
                VALUES
                    (:acc, :pass, :name)
                """;
        HashMap<String,String> args = new HashMap<>();
        args.put("acc", member.getAccount());
        args.put("pass", BCrypt.hashpw(member.getPasswd(), BCrypt.gensalt()));
        args.put("name", member.getName());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int n = jdbc.update(sql, new MapSqlParameterSource(args), keyHolder);
        if (n > 0) {
            System.out.printf("%d\n", keyHolder.getKey().intValue());
        }
    }


}

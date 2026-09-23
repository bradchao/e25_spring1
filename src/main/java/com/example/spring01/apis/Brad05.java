package com.example.spring01.apis;

import com.example.spring01.dto.Member;
import com.example.spring01.dto.MemberResponse;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/brad05")
public class Brad05 {
    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    private MemberResponse memberResponse;

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

    @PostMapping( value = {"","/{isGetId}"})
    public MemberResponse test3(@RequestBody Member member,
                                @PathVariable(required = false) Boolean isGetId){
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
            if (isGetId == null) isGetId = false;

            System.out.printf("%d\n", keyHolder.getKey().intValue());

            if (isGetId) {
                member.setId((long) keyHolder.getKey().intValue());
            }

            member.setPasswd("xxxxx");

            memberResponse.setError(0);
            memberResponse.setMessage("Insert Success");
            memberResponse.setMember(member);

        }else{
            memberResponse.setError(-1);
            memberResponse.setMessage("Insert Failure");
            memberResponse.setMember(null);
        }
        return memberResponse;
    }

    @PostMapping("/multidata")
    public void test4(@RequestBody List<Member> members){
        //System.out.println(members.size());
        String sql = """
                INSERT INTO member
                    (account,passwd, name)
                VALUES
                    (:acc, :pass, :name)
                """;

        MapSqlParameterSource[] params = new MapSqlParameterSource[members.size()];
        for (int i=0; i<members.size(); i++){
            params[i] = new MapSqlParameterSource();
            params[i].addValue("acc", members.get(i).getAccount());
            params[i].addValue("pass", BCrypt.hashpw(members.get(i).getPasswd(),BCrypt.gensalt()));
            params[i].addValue("name", members.get(i).getName());
        }

        jdbc.batchUpdate(sql, params);
    }

    @PostMapping("/multidata2")
    public void test5(@RequestBody List<Member> members){
        for (Member member : members){
            test3(member, false);
        }
    }

    @DeleteMapping("/{id}")
    public void test6(@PathVariable Integer id){
        String sql = """
                DELETE FROM member
                WHERE id = :id
                """;
        HashMap<String, Integer> args = new HashMap<>();
        args.put("id", id);
        jdbc.update(sql, args);

    }

    @PutMapping("")
    public void test7(@RequestBody Member member){
        String sql = """
                UPDATE member
                SET name = :name
                WHERE id = :id
                """;
        HashMap<String, Object> args = new HashMap<>();
        args.put("id", member.getId());
        args.put("name", member.getName());
        jdbc.update(sql, args);

    }



}

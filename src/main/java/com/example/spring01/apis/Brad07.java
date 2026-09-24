package com.example.spring01.apis;

import com.example.spring01.dto.Gift;
import com.example.spring01.utils.GiftRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/brad07")
public class Brad07 {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    private GiftRowMapper giftRowMapper;

    @GetMapping("/")
    public List<Gift> findAll(){
        String sql = "SELECT id, name, addr, tel FROM gifts";
        return jdbc.query(sql, giftRowMapper);
    }

    @GetMapping("/{id}")
    public Gift findById(@PathVariable Long id){
        String sql = "SELECT id, name, addr, tel FROM gifts WHERE id = :id";
        HashMap<String,Long> args = new HashMap<>();
        args.put("id", id);

        List<Gift> gifts = jdbc.query(sql, args, giftRowMapper);
        Gift gift;
        if (!gifts.isEmpty()){
            gift = gifts.get(0);
            gift.setError(0);
        }else{
            gift = new Gift();
            gift.setError(-1);
        }
        return gift;

    }

    @GetMapping("/v2/{id}")
    public Gift findByIdV2(@PathVariable Long id){
        String sql = "SELECT id, name, addr, tel FROM gifts WHERE id = :id";
        HashMap<String,Long> args = new HashMap<>();
        args.put("id", id);
        Gift gift;
        try {
            gift = jdbc.queryForObject(sql, args, giftRowMapper);

        }catch(Exception e){
            gift = new Gift();
            gift.setError(-1);
        }
        return gift;
    }

    @GetMapping("/search")
    public List<Gift> findByKeyword(@RequestParam String keyword){
        String sql = """
            SELECT id, name, addr, tel 
            FROM gifts 
            WHERE name LIKE :keyword OR
            addr LIKE :keyword OR
            tel LIKE :keyword
            """;
        HashMap<String,String> args = new HashMap<>();
        args.put("keyword", "%" + keyword + "%");

        return jdbc.query(sql, args, giftRowMapper);

    }


}

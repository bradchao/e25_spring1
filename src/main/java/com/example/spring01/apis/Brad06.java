package com.example.spring01.apis;

import com.example.spring01.dto.Gift;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/brad06")
public class Brad06 {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    private RestTemplate restTemplate;

//    private RestTemplate restTemplate;
//    public Brad06(){
//        restTemplate = new RestTemplate();
//    }

    @Autowired
    private ObjectMapper mapper;


    @RequestMapping("/test1")
    public void test1(){
        jdbc.update("DELETE FROM gifts", new HashMap<>());
        System.out.println("DELETE FINISH");
        jdbc.update("ALTER TABLE gifts AUTO_INCREMENT = 1", new HashMap<>());
        System.out.println("ZERO");

        String url = "https://data.moa.gov.tw/Service/OpenData/ODwsv/ODwsvAgriculturalProduce.aspx";
        String json = restTemplate.getForObject(url, String.class);
        //System.out.print(json);

        List<Gift> gifts = mapper.readValue(json, new TypeReference<List<Gift>>() {});
        System.out.println(gifts.size());
        System.out.println(gifts.get(0).getName());

        //-----------------------
        String sql = """
                INSERT INTO gifts
                    (name,addr, tel)
                VALUES
                    (:name, :addr, :tel)
                """;

        MapSqlParameterSource[] params = new MapSqlParameterSource[gifts.size()];
        for (int i=0; i<gifts.size(); i++){
            params[i] = new MapSqlParameterSource();
            params[i].addValue("name", gifts.get(i).getName());
            params[i].addValue("addr", gifts.get(i).getAddr());
            params[i].addValue("tel", gifts.get(i).getTel());
        }

        jdbc.batchUpdate(sql, params);




    }

}

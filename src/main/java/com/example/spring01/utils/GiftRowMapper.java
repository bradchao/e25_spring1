package com.example.spring01.utils;

import com.example.spring01.dto.Gift;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GiftRowMapper implements RowMapper<Gift> {
    @Override
    public Gift mapRow(ResultSet rs, int rowNum) throws SQLException {
        Gift gift = new Gift();
        gift.setId(rs.getLong("id"));
        gift.setName(rs.getString("name"));
        gift.setAddr(rs.getString("addr"));
        gift.setTel(rs.getString("tel"));
        return gift;
    }
}

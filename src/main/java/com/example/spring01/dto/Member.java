package com.example.spring01.dto;

import lombok.Data;

@Data
public class Member {
    private Long id;
    private String account;
    private String passwd;
    private String name;
}

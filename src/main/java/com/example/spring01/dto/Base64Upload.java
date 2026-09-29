package com.example.spring01.dto;

import lombok.Data;

@Data
public class Base64Upload {
    private String fileName;
    private String contentType;
    private String base64;
}

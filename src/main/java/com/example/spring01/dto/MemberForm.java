package com.example.spring01.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class MemberForm {
    private String account;
    private List<MultipartFile> files;
}

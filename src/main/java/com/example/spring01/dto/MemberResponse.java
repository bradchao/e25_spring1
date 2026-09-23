package com.example.spring01.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/*
    {
        "error": 0,     0: OK; other: error
        "message": "xxxxxx",
        "member": member
    }
 */
@Data
@Component
public class MemberResponse {
    private int error;
    private String message;
    private Member member;
}

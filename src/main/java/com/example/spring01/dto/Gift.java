package com.example.spring01.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Gift {
        private Long id;

        @JsonProperty("Name")
        private String name;

        @JsonProperty("SalePlace")
        private String addr;

        @JsonProperty("ContactTel")
        private String tel;

        private int error;
}

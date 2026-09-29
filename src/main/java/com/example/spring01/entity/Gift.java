package com.example.spring01.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "gifts")
@Data
public class Gift {
    @Id
    private Long id;
    private String name, addr, tel;
}

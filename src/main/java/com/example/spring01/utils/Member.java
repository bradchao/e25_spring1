package com.example.spring01.utils;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Member {
    private Integer id;

    @NotNull
    @Email
    private String email;

    @NotBlank
    @Size(min = 4, max = 20)
    private String account;

    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$")
    private String passwd;

    @Past
    private Date birthday;

    @FutureOrPresent
    private LocalDate pleaveDate;

    @PositiveOrZero
    private Integer age;
}

package com.example.spring01.utils;

import jakarta.validation.constraints.*;
import lombok.Data;

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

    /*
    @Past
    private String birthday;

    @FutureOrPresent
    private String pleaveDate;
    */

    @PositiveOrZero
    private Integer age;
}

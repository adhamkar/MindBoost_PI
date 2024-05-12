package com.example.mindboost.DTOs;

import com.example.mindboost.Enums.Gender;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class UserDTO {
    private Long id;
    @NotNull
    private String userName;
    @Column(unique=true)
    private String email;
    private String role;
    @NotNull
    private String Password;
    @Column(unique=true)
    private String phone;
    @NotNull
    private Gender gender;

}

package com.example.mindboost.DTOs;

import com.example.mindboost.Enums.Gender;
import lombok.Data;


@Data
public class UserDTO {
    private Long id;
    private String userName;
    private String email;
    private String userType;
    private String Password;
    private String phone;
    private Gender gender;

}

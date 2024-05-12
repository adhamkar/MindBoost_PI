package com.example.mindboost.Entities;

import com.example.mindboost.Enums.Gender;
import com.example.mindboost.Enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

import java.util.List;
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "Role")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String Password;
    private String email;
    @Column(length = 10)
    private String phone;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Formula("Role") // Add this line
    private String role;
}

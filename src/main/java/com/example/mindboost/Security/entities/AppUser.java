package com.example.mindboost.Security.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String userName;
    @Column(unique=true)
    private String email;
    @NotNull
    private String Password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> roles;
}

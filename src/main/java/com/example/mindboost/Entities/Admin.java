package com.example.mindboost.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
//@DiscriminatorValue("Admin")
@NoArgsConstructor

public class Admin extends User{

}

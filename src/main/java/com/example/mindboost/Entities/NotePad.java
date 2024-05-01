package com.example.mindboost.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotePad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Title;
    private String Content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date CreatedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ModifieddDate;
    @ManyToOne
    private Patient patient;
}

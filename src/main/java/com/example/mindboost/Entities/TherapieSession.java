package com.example.mindboost.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TherapieSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameSession;
    private Date DateSession;
    private String patientName;
    private String TherapisteName;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Therapist therapist;
}

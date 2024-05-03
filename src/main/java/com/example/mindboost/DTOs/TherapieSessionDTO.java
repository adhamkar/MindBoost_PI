package com.example.mindboost.DTOs;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data

public class TherapieSessionDTO {

    private Long id;
    private String nameSession;
    private Date DateSession;
    private String patientName;
    private String TherapisteName;

    private PatientDTO patient;

    private TherapistDTO therapist;
}

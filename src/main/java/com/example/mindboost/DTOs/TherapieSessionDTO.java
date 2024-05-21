package com.example.mindboost.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data

public class TherapieSessionDTO {
    private Long id;
    private String nameSession;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date DateSession;
    private String patientName;
    private String TherapisteName;

    private PatientDTO patientDTO;
    private TherapistDTO therapistDTO;
}

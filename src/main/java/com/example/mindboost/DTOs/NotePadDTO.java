package com.example.mindboost.DTOs;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class NotePadDTO {

    private Long id;
    private String Title;
    private String Content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date CreatedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ModifieddDate;
    private PatientDTO patientDTO;
}

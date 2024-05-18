package com.example.mindboost.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date CreatedDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date ModifieddDate;
    private PatientDTO patientDTO;
}

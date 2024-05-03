package com.example.mindboost.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CommentDTO {

    private Long id;
    private String Comment;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date CreatedDate;
    private PatientDTO patient;
    private PostDTO postDTO;
    private TherapistDTO therapist;
}

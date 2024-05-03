package com.example.mindboost.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
public class PostDTO {

    private Long id;
    private String Content;
    private Date CreatedDate;
    private Date UpdatedDate;
    private Boolean user_visibility;
    private PatientDTO patient;
    private TherapistDTO therapist;
    private List<CommentDTO> commentDTOS;

}

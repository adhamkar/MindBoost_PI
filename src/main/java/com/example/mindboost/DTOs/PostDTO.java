package com.example.mindboost.DTOs;

import com.fasterxml.jackson.annotation.*;
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
    private String Title;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date CreatedDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date UpdatedDate;
    private Boolean user_visibility;
    private PatientDTO patientDTO;
    private TherapistDTO therapistDTO;
    private List<CommentDTO> commentDTOS;


}

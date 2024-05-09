package com.example.mindboost.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


@Data
public class PatientDTO extends UserDTO {
    @NotNull
    private int age;
    private String city;
    private String profession;
    private String medicalHistory;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<PostDTO> postDTOS;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<CommentDTO> commentDTOS;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<NotePadDTO> notePadDTOS;

    private List<TherapieSessionDTO> therapieSessionDTOS;

}

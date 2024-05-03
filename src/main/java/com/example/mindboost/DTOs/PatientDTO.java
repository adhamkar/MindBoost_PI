package com.example.mindboost.DTOs;

import lombok.Data;

import java.util.List;


@Data
public class PatientDTO extends UserDTO {

    private int age;
    private String city;
    private String profession;
    private String medicalHistory;
    List<PostDTO> postDTOS;
    private List<CommentDTO> commentDTOS;
    private List<NotePadDTO> notePadDTOS;
    private List<TherapieSessionDTO> therapieSessionDTOS;
}

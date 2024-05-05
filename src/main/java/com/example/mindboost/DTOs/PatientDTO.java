package com.example.mindboost.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
public class PatientDTO extends UserDTO {

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

   /* public void addCommentByPatient(PostDTO post, PatientDTO patientDTO, String commentText) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setPostDTO(post);
        commentDTO.setPatientDTO(patientDTO);
        commentDTO.addComment(commentText);
        commentDTO.setCreatedDate(new Date());
        userService.SaveComment(commentDTO, post.getId());
    }*/

}

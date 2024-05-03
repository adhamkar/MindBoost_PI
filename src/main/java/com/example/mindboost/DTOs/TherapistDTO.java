package com.example.mindboost.DTOs;

import com.example.mindboost.Enums.TherapistRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
public class TherapistDTO extends UserDTO {

    private TherapistRole therapistRole;
    private Boolean Aviability;
    private List<TherapieSessionDTO> therapieSessionDTOS;
    private int price;
    private String LocalAddress;
    private List<CommentDTO> commentDTOS;
    private List<PostDTO> postDTOs;


}

package com.example.mindboost.DTOs;

import com.example.mindboost.Enums.TherapistRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


@Data
public class TherapistDTO extends UserDTO {
    @NotNull
    private TherapistRole therapistRole;
    @NotNull
    private Boolean Aviability;
    private List<TherapieSessionDTO> therapieSessionDTOS;
    @NotNull
    private int price;
    private String LocalAddress;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<CommentDTO> commentDTOS;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<PostDTO> postDTOs;


}

package com.example.mindboost.Entities;

import com.example.mindboost.Enums.Gender;
import com.example.mindboost.Enums.TherapistRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
//@DiscriminatorValue("Therapiste")
@NoArgsConstructor
@AllArgsConstructor
public class Therapist extends User{
    @Enumerated(EnumType.STRING)
    private TherapistRole therapistRole;
    private Boolean Aviability;
    @OneToMany (mappedBy = "therapist", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<TherapieSession> therapieSessions;
    private int price;
    private String LocalAddress;
    @OneToMany (mappedBy = "therapist", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Comment> comments;
    @OneToMany (mappedBy = "therapist", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Post> posts;


}

package com.example.mindboost.Service;

import com.example.mindboost.DTOs.*;
import com.example.mindboost.Entities.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    /************** Users things *************/
    PatientDTO SavePatient(PatientDTO patientDTO);

    UserDTO SaveUser(UserDTO userDTo);

    TherapistDTO SaveTherapist(TherapistDTO therapistDTO);
    PatientDTO UpdatePatient(PatientDTO patientDTO);
    TherapistDTO UpdateTherapist(TherapistDTO therapistDTO);
    List<PatientDTO> PATIENT_LIST();
    List<TherapistDTO> Therapist_LIST();
    PatientDTO getpatient(Long patientID);
    TherapistDTO getTherapist(Long therapistID);
    void DeletePatient(Long patientID);
    void DeleteTherapist(Long therapistID);
    List<PatientDTO> SearchPatient(String name);
    List<TherapistDTO> SearchTherapist(String name);

    /************** Post things *************/
    PostDTO SavePost(PostDTO postDTO);
    void DeletePost(Long postID);
    Post UpdatePost(Post post);
    Post getPost(Long postID);
    List<PostDTO> Post_LIST();

    /************** Comment things *************/
    CommentDTO SaveComment(CommentDTO commentDTO);

    void DeleteComment(Long commentID);
    Comment UpdateComment(Comment comment);
    Comment getComment(Long commentID);
    List<Comment> Comment_LIST();
    List<Comment> COMMENT_LIST_PERPost(Long postId);

    /************** NotePad things *************/
    NotePad SaveNotePad(NotePad notePad);
    void DeleteNotePad(Long notePadID);
    NotePad UpdateNotePad(NotePad notePad);
    NotePad getNotePad(Long notePadID);
    List<NotePad> NotePad_LIST();
    List<NotePad> SearchNotePad(String name);

    /************** TherapieSession things *************/
    TherapieSession SaveTherapieSession(TherapieSession therapieSession);
    void DeleteTherapieSession(Long therapieSessionID);
    TherapieSession UpdateTherapieSession(TherapieSession therapieSession);
    TherapieSession getTherapieSession(Long therapieSessionID);
    List<TherapieSession> TherapieSession_LIST();

    List<TherapieSession> SearchTherapieSession(String SessionName);
}

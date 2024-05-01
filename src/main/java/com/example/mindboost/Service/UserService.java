package com.example.mindboost.Service;

import com.example.mindboost.Entities.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    /************** Users things *************/
    Patient SavePatient(Patient patient);
    Therapist SaveTherapist(Therapist therapist);
    Patient UpdatePatient(Patient patient);
    Therapist UpdateTherapist(Therapist therapist);
    List<Patient> PATIENT_LIST();
    List<Therapist> Therapist_LIST();
    Patient getpatient(Long patientID);
    Therapist getTherapist(Long therapistID);
    void DeletePatient(Long patientID);
    void DeleteTherapist(Long therapistID);
    List<Patient> SearchPatient(String name);
    List<Therapist> SearchTherapist(String name);

    /************** Post things *************/
    Post SavePost(Post post);
    void DeletePost(Long postID);
    Post UpdatePost(Post post);
    Post getPost(Long postID);
    List<Post> Post_LIST();

    /************** Comment things *************/
    Comment SaveComment(Comment comment);

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

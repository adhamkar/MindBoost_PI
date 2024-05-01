package com.example.mindboost.Service;

import com.example.mindboost.Entities.*;
import com.example.mindboost.Repositories.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    /************** Repositories *************/
    private CommentRepo commentRepo;
    private PostRepo postRepo;
    private PatientRepo patientRepo;
    private TherapistRepo therapistRepo;
    private TherapySessionRepo therapySessionRepo;
    private NotepadRepo notepadRepo;
    /************** Users implementation *************/
    @Override
    public Patient SavePatient(Patient patient) {
        List<Patient> allpatients = patientRepo.findAll();
        if(allpatients.contains(patient)){
            return null;
        }
        return patientRepo.save(patient);
    }

    @Override
    public Therapist SaveTherapist(Therapist therapist) {
        List<Therapist> alltherapist = therapistRepo.findAll();
        if(alltherapist.contains(therapist)){
            return null;
        }
        return therapistRepo.save(therapist);

    }

    @Override
    public Patient UpdatePatient(Patient patient) {
        List<Patient> allpatients = patientRepo.findAll();
        if(allpatients.contains(patient)){
            return patientRepo.save(patient);
        }
        return null;
    }

    @Override
    public Therapist UpdateTherapist(Therapist therapist) {
        List<Therapist> alltherapist = therapistRepo.findAll();
        if(alltherapist.contains(therapist)){
            return therapistRepo.save(therapist);
        }
        return null;
    }

    @Override
    public List<Patient> PATIENT_LIST() {
        return patientRepo.findAll();
    }

    @Override
    public List<Therapist> Therapist_LIST() {
        return therapistRepo.findAll();
    }

    @Override
    public Patient getpatient(Long patientID) {
        return patientRepo.findById(patientID).orElseThrow(()->
                new RuntimeException("patient not found"));
    }

    @Override
    public Therapist getTherapist(Long therapistID) {
        return therapistRepo.findById(therapistID).orElseThrow(()->
                new RuntimeException("patient not found"));
    }

    @Override
    public void DeletePatient(Long patientID) {
        if(patientRepo.findById(patientID)!=null){
            patientRepo.deleteById(patientID);
        }
    }

    @Override
    public void DeleteTherapist(Long therapistID) {
        if(therapistRepo.findById(therapistID)!=null){
            patientRepo.deleteById(therapistID);
        }
    }

    @Override
    public List<Patient> SearchPatient(String name) {
        return patientRepo.searchPatientByUserName(name);
    }

    @Override
    public List<Therapist> SearchTherapist(String name) {
        return therapistRepo.searchTherapistByUserName(name);
    }

    /************** Post implementation *************/
    @Override
    public Post SavePost(Post post) {
            return postRepo.save(post);
    }

    @Override
    public void DeletePost(Long postID) {
        if(postRepo.findById(postID)!=null){
            postRepo.deleteById(postID);
        }
        System.out.println("User's post not found to delete");
    }

    @Override
    public Post UpdatePost(Post post) {
        List<Post> posts = postRepo.findAll();
        if(posts.contains(post)){
            return postRepo.save(post);
        }
        return null;
    }

    @Override
    public Post getPost(Long postID) {
        return postRepo.findById(postID).orElseThrow(()->
                new RuntimeException("post not found"));
    }

    @Override
    public List<Post> Post_LIST() {
        return postRepo.findAll();
    }

    /************** Comments implementation *************/
    @Override
    public Comment SaveComment(Comment comment) {
        if(comment.getPost()!=null){
            return  commentRepo.save(comment);
        }
        return null;
    }

    @Override
    public void DeleteComment(Long commentID) {
        commentRepo.deleteById(commentID);
    }

    @Override
    public Comment UpdateComment(Comment comment) {
        List<Comment> comments = commentRepo.findAll();
        if(comments.contains(comment)){
            commentRepo.save(comment);
        }
        return null;
    }

    @Override
    public Comment getComment(Long commentID) {
        return commentRepo.findById(commentID).orElseThrow(()->
                new RuntimeException("comment not found"));
    }

    @Override
    public List<Comment> Comment_LIST() {
        return commentRepo.findAll();
    }

    @Override
    public List<Comment> COMMENT_LIST_PERPost(Long postId) {
        Post post=postRepo.findById(postId).get();
        List<Comment> PostComments = commentRepo.findCommentsByPost(post);
        return PostComments;
    }

    /************** NotePad implementation *************/
    @Override
    public NotePad SaveNotePad(NotePad notePad) {
        return notepadRepo.save(notePad);
    }

    @Override
    public void DeleteNotePad(Long notePadID) {
        notepadRepo.deleteById(notePadID);
    }

    @Override
    public NotePad UpdateNotePad(NotePad notePad) {
       if(notepadRepo.findAll().contains(notePad)){
           return notepadRepo.save(notePad);
       }
        return null;
    }

    @Override
    public NotePad getNotePad(Long notePadID) {
        return notepadRepo.findById(notePadID).orElseThrow(()->
                new RuntimeException("Notepad not found"));
    }

    @Override
    public List<NotePad> NotePad_LIST() {
        return notepadRepo.findAll();
    }

    @Override
    public List<NotePad> SearchNotePad(String title) {
        return notepadRepo.searchByTitle(title);
    }
    /************** TherapieSession implementation *************/
    @Override
    public TherapieSession SaveTherapieSession(TherapieSession therapieSession) {
        if(therapieSession.getTherapist().getAviability() && therapieSession.getPatient()!=null && therapieSession.getTherapist()!=null){
            return therapySessionRepo.save(therapieSession);
        }
        return null;
    }

    @Override
    public void DeleteTherapieSession(Long therapieSessionID) {
        if(therapySessionRepo.findById(therapieSessionID)!=null){
            therapySessionRepo.deleteById(therapieSessionID);
        }
    }

    @Override
    public TherapieSession UpdateTherapieSession(TherapieSession therapieSession) {
        List<TherapieSession> sessions = therapySessionRepo.findAll();
        if(sessions.contains(therapieSession)){
            therapySessionRepo.save(therapieSession);
        }
        return null;
    }

    @Override
    public TherapieSession getTherapieSession(Long therapieSessionID) {
        return therapySessionRepo.findById(therapieSessionID).orElseThrow(()->
                new RuntimeException("Session not found"));
    }

    @Override
    public List<TherapieSession> TherapieSession_LIST() {
        return therapySessionRepo.findAll();
    }

    @Override
    public List<TherapieSession> SearchTherapieSession(String SessionName) {
        return therapySessionRepo.searchTherapieSessionByNameSession(SessionName);
    }
}

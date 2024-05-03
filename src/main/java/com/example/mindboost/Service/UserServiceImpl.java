package com.example.mindboost.Service;

import com.example.mindboost.DTOs.*;
import com.example.mindboost.Entities.*;
import com.example.mindboost.Mappers.MapperImpl;
import com.example.mindboost.Repositories.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Console;
import java.util.List;
import java.util.stream.Collectors;

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
    private MapperImpl mapper;
    private UserRepo userRepo;
    /************** Users implementation *************/
    @Override
    public UserDTO SaveUser(UserDTO userDTo){
        User user=mapper.FromUserDTO(userDTo);
        User userSaved=userRepo.save(user);
        return mapper.FromUser(userSaved);
    }
    @Override
    public PatientDTO SavePatient(PatientDTO patientDTO) {
        Patient patient=mapper.FromPatientDTO(patientDTO);
        List<Patient> allpatients = patientRepo.findAll();

        if(allpatients.contains(patient)){
            return null;
        }
        Patient savedPatient = patientRepo.save(patient);
        patientDTO.setId(savedPatient.getId()); // Mise à jour de l'ID de patientDTO
        return patientDTO;
    }


    @Override
    public TherapistDTO SaveTherapist(TherapistDTO therapistDTO) {
        Therapist therapist=mapper.FromTherapistDTO(therapistDTO);
        List<Therapist> alltherapist = therapistRepo.findAll();
        if(alltherapist.contains(therapist)){
            return null;
        }
        Therapist savedTherapist=therapistRepo.save(therapist);
        return mapper.FromTherapist(savedTherapist);

    }

    @Override
    public PatientDTO UpdatePatient(PatientDTO patientDTO) {
        Patient patient=mapper.FromPatientDTO(patientDTO);
        List<Patient> allpatients = patientRepo.findAll();
        if(allpatients.contains(patient)){
            Patient savedPatient=patientRepo.save(patient);
            return mapper.FromPatient(savedPatient);
        }
        return null;
    }

    @Override
    public TherapistDTO UpdateTherapist(TherapistDTO therapistDTO) {
        Therapist therapist=mapper.FromTherapistDTO(therapistDTO);
        List<Therapist> alltherapist = therapistRepo.findAll();
        if(alltherapist.contains(therapist)){
            Therapist savedTherapist=therapistRepo.save(therapist);
            return mapper.FromTherapist(savedTherapist);
        }
        return null;
    }

    @Override
    public List<PatientDTO> PATIENT_LIST() {
        List<Patient> patients=patientRepo.findAll();
        List<PatientDTO> patientDTOS=patients.stream()
                .map(patient->mapper.FromPatient(patient))
                .collect(Collectors.toList());
        return patientDTOS;
    }

    @Override
    public List<TherapistDTO> Therapist_LIST() {
        List<Therapist> therapistes = therapistRepo.findAll();
        List<TherapistDTO> therapistDTOS=therapistes.stream()
                .map(therapist->mapper.FromTherapist(therapist))
                .collect(Collectors.toList());
        return therapistDTOS;
    }

    @Override
    public PatientDTO getpatient(Long patientID) {
        Patient patient=patientRepo.findById(patientID)
                .orElseThrow(()->new RuntimeException("patient not found"));
        return mapper.FromPatient(patient);
    }

    @Override
    public TherapistDTO getTherapist(Long therapistID) {
        Therapist therapist=therapistRepo.findById(therapistID)
                .orElseThrow(()->new RuntimeException("Therapist not found"));
        return mapper.FromTherapist(therapist);
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
    public List<PatientDTO> SearchPatient(String name) {
        List<Patient> patients=patientRepo.searchPatientByUserName(name);
        List<PatientDTO> patientDTOS=patients.stream()
                .map(patient->mapper.FromPatient(patient))
                .collect(Collectors.toList());
        return patientDTOS;
    }

    @Override
    public List<TherapistDTO> SearchTherapist(String name) {
        List<Therapist> therapistes = therapistRepo.searchTherapistByUserName(name);
        List<TherapistDTO> therapistDTOS=therapistes.stream()
                .map(therapist->mapper.FromTherapist(therapist))
                .collect(Collectors.toList());
        return therapistDTOS;
    }

    @Override
    public PostDTO SavePost(PostDTO postDTO) {
        return null;
    }

    /************** Post implementation *************/
    @Override
    public PostDTO SavePost(PostDTO postDTO, Long Patient_id) {
       Patient  pt = patientRepo.findById(Patient_id).orElse(null);
        if (pt == null)
             log.info("Patient not found with ID: " + Patient_id);
        Post post=mapper.FromPostDTO(postDTO);
        post.setPatient(pt);
        Post savedPost=postRepo.save(post);
            return mapper.FromPost(savedPost);
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
    public List<PostDTO> Post_LIST() {
        List<Post> posts = postRepo.findAll();
        List<PostDTO> postDTOS=posts.stream()
                .map(post->mapper.FromPost(post))
                .collect(Collectors.toList());
        return postDTOS;
    }

    /************** Comments implementation *************/
    @Override
    public CommentDTO SaveComment(CommentDTO commentDTO) {
        if(commentDTO.getPostDTO()!=null){

            Comment comment=mapper.FromCommentDTO(commentDTO);
            Comment savedComment=commentRepo.save(comment);
            return  mapper.FromComment(savedComment);
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

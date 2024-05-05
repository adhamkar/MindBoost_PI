package com.example.mindboost.Service;

import com.example.mindboost.DTOs.*;
import com.example.mindboost.Entities.*;
import com.example.mindboost.Mappers.MapperImpl;
import com.example.mindboost.Repositories.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public UserDTO SaveUser(UserDTO userDTo) {
        User user = mapper.FromUserDTO(userDTo);
        User userSaved = userRepo.save(user);
        return mapper.FromUser(userSaved);
    }

    @Override
    public PatientDTO SavePatient(PatientDTO patientDTO) {
        Patient patient = mapper.FromPatientDTO(patientDTO);
        List<Patient> allpatients = patientRepo.findAll();

        if (allpatients.contains(patient)) {
            return null;
        }
        Patient savedPatient = patientRepo.save(patient);
        patientDTO.setId(savedPatient.getId()); // Mise à jour de l'ID de patientDTO
        return patientDTO;
    }


    @Override
    public TherapistDTO SaveTherapist(TherapistDTO therapistDTO) {
        Therapist therapist = mapper.FromTherapistDTO(therapistDTO);
        List<Therapist> alltherapist = therapistRepo.findAll();
        if (alltherapist.contains(therapist)) {
            return null;
        }
        Therapist savedTherapist = therapistRepo.save(therapist);
        therapistDTO.setId((savedTherapist.getId()));
        return therapistDTO;

    }

    @Override
    public PatientDTO UpdatePatient(PatientDTO patientDTO) {
        Patient patient = mapper.FromPatientDTO(patientDTO);
        List<Patient> allpatients = patientRepo.findAll();
        if (allpatients.contains(patient)) {
            Patient savedPatient = patientRepo.save(patient);
            return mapper.FromPatient(savedPatient);
        }
        return null;
    }

    @Override
    public TherapistDTO UpdateTherapist(TherapistDTO therapistDTO) {
        Therapist therapist = mapper.FromTherapistDTO(therapistDTO);
        List<Therapist> alltherapist = therapistRepo.findAll();
        if (alltherapist.contains(therapist)) {
            Therapist savedTherapist = therapistRepo.save(therapist);
            return mapper.FromTherapist(savedTherapist);
        }
        return null;
    }

    @Override
    public List<PatientDTO> PATIENT_LIST() {
        List<Patient> patients = patientRepo.findAll();
        List<PatientDTO> patientDTOS = patients.stream()
                .map(patient -> mapper.FromPatient(patient))
                .collect(Collectors.toList());
        return patientDTOS;
    }

    @Override
    public List<TherapistDTO> Therapist_LIST() {
        List<Therapist> therapistes = therapistRepo.findAll();
        List<TherapistDTO> therapistDTOS = therapistes.stream()
                .map(therapist -> mapper.FromTherapist(therapist))
                .collect(Collectors.toList());
        return therapistDTOS;
    }

    @Override
    public PatientDTO getpatient(Long patientID) {
        Patient patient = patientRepo.findById(patientID)
                .orElseThrow(() -> new RuntimeException("patient not found"));
        return mapper.FromPatient(patient);
    }

    @Override
    public TherapistDTO getTherapist(Long therapistID) {
        Therapist therapist = therapistRepo.findById(therapistID)
                .orElseThrow(() -> new RuntimeException("Therapist not found"));
        return mapper.FromTherapist(therapist);
    }

    @Override
    public void DeletePatient(Long patientID) {
        if (patientRepo.findById(patientID) != null) {
            patientRepo.deleteById(patientID);
        }
    }

    @Override
    public void DeleteTherapist(Long therapistID) {
        if (therapistRepo.findById(therapistID) != null) {
            patientRepo.deleteById(therapistID);
        }
    }

    @Override
    public List<PatientDTO> SearchPatient(String name) {
        List<Patient> patients = patientRepo.searchPatientByUserName(name);
        List<PatientDTO> patientDTOS = patients.stream()
                .map(patient -> mapper.FromPatient(patient))
                .collect(Collectors.toList());
        return patientDTOS;
    }

    @Override
    public List<TherapistDTO> SearchTherapist(String name) {
        List<Therapist> therapistes = therapistRepo.searchTherapistByUserName(name);
        List<TherapistDTO> therapistDTOS = therapistes.stream()
                .map(therapist -> mapper.FromTherapist(therapist))
                .collect(Collectors.toList());
        return therapistDTOS;
    }

    /************** Post implementation *************/
    @Override
    public PostDTO savePost(PostDTO postDTO){
        PatientDTO patientDTO = postDTO.getPatientDTO();
        Patient patient = patientRepo.findById(patientDTO.getId()).orElse(null);
        if (patient == null) {
            log.info("Patient not found with ID: " + patientDTO.getId());
            return null;
        }
        Post post=mapper.FromPostDTO(postDTO);
        post.setPatient(patient);
        Post savedPost=postRepo.save(post);
        postDTO.setId(savedPost.getId());
        return mapper.FromPost(savedPost);
    }


    @Override
    public PostDTO savePost_Therapist(PostDTO postDTO){
        TherapistDTO therapistDTO=postDTO.getTherapistDTO();
        Therapist therapist=therapistRepo.findById(therapistDTO.getId()).orElseThrow(null);
        if(therapist==null){
            log.info("Therapist not found with ID: " + therapistDTO.getId());
            return null;
        }
        Post post=mapper.FromPostDTO(postDTO);
        post.setTherapist(therapist);
        Post savedPost=postRepo.save(post);
        postDTO.setId(savedPost.getId());
        return mapper.FromPost(savedPost);
    }

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
    public PostDTO UpdatePost(PostDTO postDTO) {
       /* List<Post> posts = postRepo.findAll();
        if(posts.contains(post)){
            return postRepo.save(post);
        }*/
        Post post=mapper.FromPostDTO(postDTO);
        Post savedPost=postRepo.save(post);
        return mapper.FromPost(savedPost);
    }

    @Override
    public PostDTO getPost(Long postID) {
        Post post=postRepo.findById(postID).orElseThrow(null);
        if(post!=null){
           return mapper.FromPost(post);
        }
        return null;
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
    public CommentDTO SaveComment(CommentDTO commentDTO,Long Post_id) {
        Post post=postRepo.findById(Post_id).orElseThrow(null);
        if(commentDTO==null){
            return null;
        }
        Comment comment=mapper.FromCommentDTO(commentDTO);
        comment.setPost(post);
        comment.setTherapist(post.getTherapist());
        comment.setPatient(post.getPatient());
        Comment savedComment=commentRepo.save(comment);
        return  mapper.FromComment(savedComment);
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
    public List<CommentDTO> Comment_LIST() {

        List<Comment> comments=commentRepo.findAll();
        List<CommentDTO> commentDTOS=comments.stream()
                .map(comment -> mapper.FromComment(comment))
                .collect(Collectors.toList());
        return commentDTOS;
    }

    @Override
    public List<CommentDTO> COMMENT_LIST_PERPost(Long postId) {
        /*
        List<Comment> comments=commentRepo.findAll();
        List<CommentDTO> commentDTOS=comments.stream()
                .map(comment -> mapper.FromComment(comment))
                .collect(Collectors.toList());
         */
        Post post=postRepo.findById(postId).orElseThrow(null);
        List<Comment> postComments = commentRepo.findCommentsByPost(post);
        List<CommentDTO> commentDTOS=postComments.stream()
                .map(comment -> mapper.FromComment(comment))
                .collect(Collectors.toList());
        return commentDTOS;
    }

    /************** NotePad implementation *************/
    @Override
    public NotePadDTO SaveNotePad(NotePadDTO notePadDTO) {
        PatientDTO patientDTO = notePadDTO.getPatientDTO();
        Patient patient = patientRepo.findById(patientDTO.getId()).orElse(null);
        if (patient == null) {
            log.info("Patient not found with ID: " + patientDTO.getId());
            return null;
        }
        NotePad notePad=mapper.FromNotePadDTO(notePadDTO);
        notePad.setPatient(patient);
        NotePad savedNotePad= notepadRepo.save(notePad);
        notePadDTO.setId(savedNotePad.getId());
        return mapper.FromNotePad(savedNotePad);
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
    public TherapieSessionDTO SaveTherapieSession(TherapieSessionDTO therapieSessionDTO) {
        PatientDTO patientDTO = therapieSessionDTO.getPatientDTO();
        Patient patient = patientRepo.findById(patientDTO.getId()).orElse(null);

        TherapistDTO therapistDTO=therapieSessionDTO.getTherapistDTO();
        Therapist therapist=therapistRepo.findById(therapistDTO.getId()).orElseThrow(null);

        if (patient == null || therapist==null) {
            return null;
        }

        TherapieSession therapieSession=mapper.FromTherapySessionDTO(therapieSessionDTO);
        therapieSession.setPatient(patient);
        therapieSession.setTherapist(therapist);
        TherapieSession SavedSession=therapySessionRepo.save(therapieSession);
        return mapper.FromTherapySession(SavedSession);
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

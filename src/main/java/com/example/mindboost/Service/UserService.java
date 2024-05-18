package com.example.mindboost.Service;

import com.example.mindboost.DTOs.*;
import com.example.mindboost.Entities.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    UserDTO LoadUserByUserName(String userName);

    List<UserDTO> User_LIST();

    UserDTO getUser(Long userID);

    void DeleteUser(Long userID);

    UserDTO UpdateUser(UserDTO userDTO);

    AdminDTO SaveAdmin(AdminDTO adminDTO);

    AdminDTO UpdateAdmin(AdminDTO adminDTO);

    List<AdminDTO> Admin_LIST();

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

    //    @Override
//    public PostDTO SavePost(PostDTO postDTO, Long Patient_id) {
//        Patient  pt = patientRepo.findById(Patient_id).orElse(null);
//        if (pt == null)
//            log.info("Patient not found with ID: " + Patient_id);
//        Post post=mapper.FromPostDTO(postDTO);
//        post.setPatient(pt);
//        Post savedPost=postRepo.save(post);
//        postDTO = mapper.FromPost(savedPost);
//        Patient patient = postRepo.getPatientByPostId(postDTO.getId());
//        postDTO.getPatient().setId(patient.getId());
//        //postDTO.setPatient(postRepo.getPatientByPostId(postDTO.getId()));
//        return postDTO;
//    }
    PostDTO savePost(PostDTO postDTO);

    PostDTO savePost_Therapist(PostDTO postDTO);

    /************** Post things *************/
    PostDTO SavePost(PostDTO postDTO,Long Patient_id);
    void DeletePost(Long postID);
    PostDTO UpdatePost(PostDTO postDTO);
    PostDTO getPost(Long postID);

    List<PostDTO> Post_LIST_PERPatient(Long patientID);

    List<PostDTO> Post_List_PERTherapist(Long therapistID);

    List<PostDTO> Post_LIST();



    /************** Comment things *************/
    CommentDTO SaveComment(CommentDTO commentDTO,Long Post_id);

    void DeleteComment(Long commentID);
    CommentDTO UpdateComment(CommentDTO commentDTO);
    CommentDTO getComment(Long commentID);
    List<CommentDTO> Comment_LIST();
    List<CommentDTO> COMMENT_LIST_PERPost(Long postId);

    /************** NotePad things *************/
    //NotePad SaveNotePad(NotePad notePad);
    NotePadDTO SaveNotePad(NotePadDTO notePadDTO);
    void DeleteNotePad(Long notePadID);
    NotePadDTO UpdateNotePad(NotePadDTO notePadDTO);
    NotePadDTO getNotePad(Long notePadID);
    List<NotePadDTO> NotePad_LIST();
    List<NotePad> SearchNotePad(String name);

    /************** TherapieSession things *************/
    TherapieSessionDTO SaveTherapieSession(TherapieSessionDTO therapieSessionDTO);
    void DeleteTherapieSession(Long therapieSessionID);
    TherapieSessionDTO UpdateTherapieSession(TherapieSessionDTO therapieSession);
    TherapieSessionDTO getTherapieSession(Long therapieSessionID);
    List<TherapieSessionDTO> TherapieSession_LIST();

    List<TherapieSessionDTO> SearchTherapieSession(String SessionName);

    List<NotePadDTO> NotePad_LIST_PERPatient(Long id);

    PatientDTO findPatientByUsername(String username);
}

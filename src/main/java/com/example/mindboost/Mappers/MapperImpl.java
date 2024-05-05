package com.example.mindboost.Mappers;

import com.example.mindboost.DTOs.*;
import com.example.mindboost.Entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MapperImpl {
    public UserDTO FromUser(User user){
        UserDTO userDTO=new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }

    public User FromUserDTO(UserDTO userDTO){
        User user=new User();
        BeanUtils.copyProperties(userDTO,user);
        return user;
    }
    public PatientDTO FromPatient(Patient patient){
        PatientDTO patientDTO=new PatientDTO();
        BeanUtils.copyProperties(patient,patientDTO);
        return patientDTO;
    }
    public Patient FromPatientDTO(PatientDTO patientDTO){
        Patient patient=new Patient();
        BeanUtils.copyProperties(patientDTO,patient);
        return patient;
    }
    public TherapistDTO FromTherapist(Therapist therapist){
        TherapistDTO therapistDTO=new TherapistDTO();
        BeanUtils.copyProperties(therapist,therapistDTO);
       // therapistDTO.setId(therapist.getId());
        return therapistDTO;
    }
    public Therapist FromTherapistDTO(TherapistDTO therapistDTO){
        Therapist therapist=new Therapist();
        BeanUtils.copyProperties(therapistDTO,therapist);
        return therapist;
    }
    public PostDTO FromPost(Post post){
        PostDTO postDTO=new PostDTO();
        BeanUtils.copyProperties(post,postDTO);
        return postDTO;
    }
    public Post FromPostDTO(PostDTO postDTO){
        Post post=new Post();
        BeanUtils.copyProperties(postDTO,post);
        return post;
    }
    public NotePadDTO FromNotePad(NotePad notePad){
        NotePadDTO notePadDTO=new NotePadDTO();
        BeanUtils.copyProperties(notePad,notePadDTO);
        return notePadDTO;
    }
    public NotePad FromNotePadDTO(NotePadDTO notePadDTO){
        NotePad notePad=new NotePad();
        BeanUtils.copyProperties(notePadDTO,notePad);
        return notePad;
    }
    public CommentDTO FromComment(Comment comment){
        CommentDTO commentDTO=new CommentDTO();
        BeanUtils.copyProperties(comment,commentDTO);
        return commentDTO;
    }
    public Comment FromCommentDTO(CommentDTO commentDTO){
        Comment comment=new Comment();
        BeanUtils.copyProperties(commentDTO,comment);
        return comment;
    }

    public TherapieSessionDTO FromTherapySession(TherapieSession therapieSession){
        TherapieSessionDTO therapieSessionDTO = new TherapieSessionDTO();
        BeanUtils.copyProperties(therapieSession,therapieSessionDTO);
        return therapieSessionDTO;
    }

    public TherapieSession FromTherapySessionDTO(TherapieSessionDTO therapieSessionDTO){
        TherapieSession therapieSession = new TherapieSession();
        BeanUtils.copyProperties(therapieSessionDTO,therapieSession);
        return therapieSession;
    }
}

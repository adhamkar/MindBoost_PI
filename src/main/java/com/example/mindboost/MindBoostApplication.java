package com.example.mindboost;

import com.example.mindboost.DTOs.*;
import com.example.mindboost.Entities.*;
import com.example.mindboost.Enums.Gender;
import com.example.mindboost.Enums.Role;
import com.example.mindboost.Enums.TherapistRole;
import com.example.mindboost.Repositories.*;
import com.example.mindboost.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@SpringBootApplication
public class MindBoostApplication {

    public static void main(String[] args) {
        SpringApplication.run(MindBoostApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(UserService userService){

        return args -> {
            Random random=new Random();
            Gender[] genders = Gender.values();
            Role[] roles=Role.values();
            TherapistRole[] therapistRoles=TherapistRole.values();

            Stream.of("Adham","Aymane","Malika").forEach(name->{
                PatientDTO patientDTO=new PatientDTO();
                patientDTO.setUserName(name);
                patientDTO.setCity("Rabat");
                patientDTO.setAge(20);
                patientDTO.setPassword((name+random.nextInt(1000)+1));
                patientDTO.setProfession("Etudiant");
                StringBuilder phoneNumber = new StringBuilder("06");
                for (int j = 0; j < 8; j++) {
                    phoneNumber.append(random.nextInt(10));
                }
                patientDTO.setPhone(phoneNumber.toString());
                patientDTO.setEmail(name+"@gmail.com");
                int i = random.nextInt(genders.length);
                patientDTO.setGender(genders[i]);
                patientDTO.setMedicalHistory("medical information");

                userService.SavePatient(patientDTO);

                System.out.println("patient id:"+patientDTO.getId());

                PostDTO postDTO=new PostDTO();
                postDTO.setPatientDTO(patientDTO);
                postDTO.setCreatedDate(new Date());
                postDTO.setContent("this is "+patientDTO.getUserName()+"'s post");
                postDTO.setUser_visibility(random.nextBoolean());
                userService.savePost(postDTO);
                System.out.println("Id du patient lié au post:"+postDTO.getPatientDTO().getId());

                NotePadDTO notePadDTO=new NotePadDTO();
                notePadDTO.setPatientDTO(patientDTO);
                notePadDTO.setContent(patientDTO.getUserName() +"'s notepad content");
                notePadDTO.setCreatedDate(new Date());
                notePadDTO.setTitle(patientDTO.getUserName() +"'s notepad title");
                userService.SaveNotePad(notePadDTO);



            });

            Stream.of("Ikram","Meryem","Sara").forEach(name->{
                TherapistDTO therapistDTO=new TherapistDTO();
                therapistDTO.setUserName(name);
                therapistDTO.setPassword(name+random.nextInt(1000)+1);
                therapistDTO.setEmail(name+"@gmail.com");
                int i = random.nextInt(genders.length);
                therapistDTO.setGender(genders[i]);
                int tr= random.nextInt(therapistRoles.length);
                therapistDTO.setTherapistRole(therapistRoles[tr]);
                StringBuilder phoneNumber = new StringBuilder("06");
                for (int j = 0; j < 8; j++) {
                    phoneNumber.append(random.nextInt(10));
                }
                therapistDTO.setPhone(phoneNumber.toString());
                therapistDTO.setAviability(random.nextBoolean());
                therapistDTO.setPrice(250);
                therapistDTO.setLocalAddress(name+"'s local address");
                userService.SaveTherapist(therapistDTO);
                System.out.println("Therapist id :"+therapistDTO.getId());

                PostDTO postDTO=new PostDTO();
                postDTO.setTherapistDTO(therapistDTO);
                postDTO.setCreatedDate(new Date());
                postDTO.setContent("this is "+therapistDTO.getUserName()+"'s post");
                postDTO.setUser_visibility(random.nextBoolean());
                userService.savePost_Therapist(postDTO);
                System.out.println("Post Therapist id :"+postDTO.getTherapistDTO().getId());



            });
            System.out.println("*******************************************************");

            userService.Post_LIST().forEach(post -> {
                CommentDTO commentDTO=new CommentDTO();
                commentDTO.setPostDTO(post);
                System.out.println("Post id :"+ commentDTO.getPostDTO().getId());
                if(post.getTherapistDTO()!=null && post.getPatientDTO()==null){
                    commentDTO.setTherapistDTO(post.getTherapistDTO());
                    commentDTO.setComment(post.getTherapistDTO().getUserName()+" therapist comment 1");

                }
                if(post.getTherapistDTO()==null && post.getPatientDTO()!=null){
                    commentDTO.setPatientDTO(post.getPatientDTO());
                    commentDTO.setComment(post.getPatientDTO().getUserName()+" patient comment 1");

                }
                commentDTO.setCreatedDate(new Date());
                userService.SaveComment(commentDTO,post.getId());

                if(commentDTO.getTherapistDTO()!=null && commentDTO.getPatientDTO()==null){
                    System.out.println("Therapist id :"+commentDTO.getTherapistDTO().getId());

                }
                if(post.getTherapistDTO()==null && post.getPatientDTO()!=null){
                    System.out.println("Patient id :"+commentDTO.getPatientDTO().getId());
                }

            });

            PatientDTO patientDTO=userService.getpatient(1L);
            TherapistDTO therapistDTO=userService.getTherapist(6L);
            if(therapistDTO.getAviability()){
                TherapieSessionDTO therapieSessionDTO=new TherapieSessionDTO();
                therapieSessionDTO.setTherapistDTO(therapistDTO);
                therapieSessionDTO.setPatientDTO(patientDTO);
                therapieSessionDTO.setDateSession(new Date());
                therapieSessionDTO.setPatientName(patientDTO.getUserName());
                therapieSessionDTO.setTherapisteName(therapistDTO.getUserName());
                therapieSessionDTO.setNameSession("sessiono 1");
                userService.SaveTherapieSession(therapieSessionDTO);
                System.out.println("Therapist available, Session saved");
            }else{
                System.out.println("Therapist not available");
            }
            PostDTO post = userService.getPost(1L);
            List<CommentDTO> commentDTOS = userService.COMMENT_LIST_PERPost(post.getId());
            commentDTOS.forEach(commentDTO -> {
                System.out.println("Post avec id :"+post.getId()+" Commentaires :"+commentDTO.getComment());
            });

        };
    }


}

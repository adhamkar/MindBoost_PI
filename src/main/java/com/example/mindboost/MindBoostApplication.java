package com.example.mindboost;

import com.example.mindboost.DTOs.CommentDTO;
import com.example.mindboost.DTOs.PatientDTO;
import com.example.mindboost.DTOs.PostDTO;
import com.example.mindboost.DTOs.TherapistDTO;
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
                    postDTO.setPatient(patientDTO);
                    postDTO.setCreatedDate(new Date());
                    postDTO.setContent("this is "+patientDTO.getUserName()+"'s post");
                    postDTO.setUser_visibility(random.nextBoolean());
                    userService.SavePost(postDTO);
                    System.out.println("Id du patient lié au post:"+postDTO.getPatient().getId());



                /*
                Stream.of("comment1","comment3","comment2").forEach(c->{
                    CommentDTO commenntDTO=new CommentDTO();
                    commenntDTO.setComment(c);
                    commenntDTO.setPostDTO(postDTO);
                    commenntDTO.setCreatedDate(new Date());
                    commenntDTO.setPatient(patientDTO);
                    userService.SaveComment(commenntDTO);
                });*/

            });
            /*
            List<PatientDTO> patientDTOS = userService.PATIENT_LIST();

            patientDTOS.forEach(p->{
                PostDTO postDTO=new PostDTO();
                postDTO.setPatient(p);
                postDTO.setCreatedDate(new Date());
                postDTO.setContent("this is "+p.getUserName()+"'s post");
                postDTO.setUser_visibility(random.nextBoolean());
                userService.SavePost(postDTO);
            });*/

            Stream.of("Ikram","Karim","Sara").forEach(name->{
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

                PostDTO postDTO=new PostDTO();
                postDTO.setTherapist(therapistDTO);
                postDTO.setTherapist(therapistDTO);
                postDTO.setCreatedDate(new Date());
                postDTO.setContent("this is "+therapistDTO.getUserName()+"'s post");
                postDTO.setUser_visibility(random.nextBoolean());
                userService.SavePost(postDTO);

            });

            userService.Therapist_LIST().forEach(therapist -> {
                userService.Post_LIST().forEach(post -> {
                    Stream.of("comment4","comment5","comment6").forEach(c->{
                        CommentDTO commentDTO=new CommentDTO();
                        commentDTO.setPostDTO(post);
                        commentDTO.setComment(c);
                        commentDTO.setTherapist(therapist);
                        commentDTO.setCreatedDate(new Date());
                        userService.SaveComment(commentDTO);
                    });
                });

            });
        };
    }

    //@Bean
    CommandLineRunner start(UserRepo userRepo,
                            PostRepo postRepo,
                            CommentRepo commentRepo,
                            NotepadRepo notepadRepo,
                            TherapistRepo therapistRepo,
                            PatientRepo patientRepo,
                            AdminRepo adminRepo,
                            TherapySessionRepo therapySessionRepo){

        Random random=new Random();
        Gender[] genders = Gender.values();
        Role[] roles=Role.values();

        TherapistRole[] therapistRoles=TherapistRole.values();

        return args -> {

            Stream.of("Adham","Aymane","Malika").forEach(name->{
                Patient patient=new Patient();
                patient.setUserName(name);
                patient.setCity("Rabat");
                patient.setAge(20);
                patient.setPassword((name+random.nextInt(1000)+1));
                patient.setProfession("Etudiant");
                StringBuilder phoneNumber = new StringBuilder("06");
                for (int j = 0; j < 8; j++) {
                    phoneNumber.append(random.nextInt(10));
                }
                patient.setPhone(phoneNumber.toString());
                patient.setEmail(name+"@gmail.com");
                int i = random.nextInt(genders.length);
                patient.setGender(genders[i]);
                patient.setMedicalHistory("medical information");
                patientRepo.save(patient);

            });
            Stream.of("Ikram","Karim","Sara").forEach(name->{
                Therapist therapist=new Therapist();
                therapist.setUserName(name);
                therapist.setPassword(name+random.nextInt(1000)+1);
                therapist.setEmail(name+"@gmail.com");
                int i = random.nextInt(genders.length);
                therapist.setGender(genders[i]);
                int tr= random.nextInt(therapistRoles.length);
                therapist.setTherapistRole(therapistRoles[tr]);
                StringBuilder phoneNumber = new StringBuilder("06");
                for (int j = 0; j < 8; j++) {
                    phoneNumber.append(random.nextInt(10));
                }
                therapist.setPhone(phoneNumber.toString());
                therapist.setAviability(random.nextBoolean());
                therapist.setPrice(250);
                therapist.setLocalAddress(name+"'s local address");
                therapistRepo.save(therapist);
            });

            patientRepo.findAll().forEach(patient -> {
                Post post=new Post();
                post.setPatient(patient);
                post.setCreatedDate(new Date());
                post.setContent("this is "+patient.getUserName()+"'s post");
                post.setUser_visibility(random.nextBoolean());
                postRepo.save(post);
            });
            postRepo.findAll().forEach(post -> {
                Comment comment=new Comment();
                comment.setPost(post);
                comment.setPatient(post.getPatient());
                comment.setComment("this is "+post.getPatient().getUserName()+"'s comment");
                comment.setCreatedDate(new Date());
                commentRepo.save(comment);
            });
            patientRepo.findAll().forEach(patient -> {
                NotePad notePad=new NotePad();
                notePad.setPatient(patient);
                notePad.setContent("this is "+patient.getUserName()+"'s notes");
                notePad.setCreatedDate(new Date());
                notePad.setTitle(patient.getUserName()+"'s title");
                notepadRepo.save(notePad);
            });

            Patient patient = patientRepo.findById(1L).orElse(null);
            Therapist therapist = therapistRepo.findById(4L).orElse(null);

            if(patient!=null && therapist!=null && therapist.getAviability()){
                TherapieSession therapieSession=new TherapieSession();
                therapieSession.setDateSession(new Date());
                therapieSession.setPatient(patient);
                therapieSession.setTherapist(therapist);
                therapieSession.setPatientName(patient.getUserName());
                therapieSession.setTherapisteName(therapist.getUserName());
                therapieSession.setNameSession("Session test");
                therapySessionRepo.save(therapieSession);
            }else{
                System.out.println("Le thérapeute sélectionné n'est pas disponible.");
            }

        };
    }
}

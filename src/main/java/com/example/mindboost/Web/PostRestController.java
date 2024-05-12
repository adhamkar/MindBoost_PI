package com.example.mindboost.Web;

import com.example.mindboost.DTOs.CommentDTO;
import com.example.mindboost.DTOs.PatientDTO;
import com.example.mindboost.DTOs.PostDTO;
import com.example.mindboost.DTOs.TherapistDTO;
import com.example.mindboost.Entities.Comment;
import com.example.mindboost.Entities.Patient;
import com.example.mindboost.Entities.Post;
import com.example.mindboost.Entities.Therapist;
import com.example.mindboost.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class PostRestController {

    private UserService userService;

    @GetMapping("/posts")
    public List<PostDTO> posts(){
        return userService.Post_LIST();
    }
    @GetMapping("/posts/{id}/title")
    public String getPostTitle(@PathVariable(name = "id") Long Id){
        return userService.getPost(Id).getTitle();
    }

    @GetMapping("/posts/{id}")
    public PostDTO getPost(@PathVariable(name = "id") Long Id){
        return userService.getPost(Id);
    }

    @GetMapping("/posts/{id}/patient")
    public PatientDTO getPatient(@PathVariable(name = "id") Long Id){
        return userService.getPost(Id).getPatientDTO();
    }

    @GetMapping("/posts/{id}/therapist")
    public TherapistDTO getTherapist(@PathVariable(name = "id") Long Id){
        return userService.getPost(Id).getTherapistDTO();
    }

    @GetMapping("/posts/{id}/comments")
    public List<CommentDTO> getPostComments(@PathVariable(name = "id") Long postId){
        /*List<CommentDTO> commentDTOS = userService.COMMENT_LIST_PERPost(postId);
        List<String> comments=new ArrayList<>();
        commentDTOS.forEach(commentDTO -> {
            comments.add(commentDTO.getComment());
        });*/
        return userService.COMMENT_LIST_PERPost(postId);
    }

    @PostMapping("/patient_post")
    public PostDTO AddPostByPatient(@RequestBody PostDTO postDTO,@RequestParam Long patientID){
        PatientDTO patientDTO = userService.getpatient(patientID);
        postDTO.setPatientDTO(patientDTO);
        postDTO.setUser_visibility(true);
        postDTO.setCreatedDate(new Date());
        return userService.savePost(postDTO);
    }

    @PostMapping("/therapist_post")
    public PostDTO AddPostByTherapist(@RequestBody PostDTO postDTO,@RequestParam Long TherapsitID){
        TherapistDTO therapistDTO = userService.getTherapist(TherapsitID);
        postDTO.setTherapistDTO(therapistDTO);
        postDTO.setUser_visibility(true);
        postDTO.setCreatedDate(new Date());
        return userService.savePost_Therapist(postDTO);
    }

    @DeleteMapping("/posts/{id}")
    public void DeletePost(@PathVariable(name = "id") Long id){
        userService.DeletePost(id);
    }

    @PatchMapping("/posts/{id}")
    public PostDTO updatePostByPatient(@RequestBody PostDTO postDTO,@PathVariable Long id){
        postDTO.setId(id);
        return userService.UpdatePost(postDTO);

    }
}

package com.example.mindboost.Web;

import com.example.mindboost.DTOs.*;
import com.example.mindboost.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")

public class CommentRestController {

    private UserService userService;
    @GetMapping("/comments")
    public List<CommentDTO> getComments(){
     return userService.Comment_LIST();
    }
    @GetMapping("/comments/{id}")
    public CommentDTO getComment(@PathVariable Long id){
        return userService.getComment(id);
    }
    @GetMapping("/comments/{id}/post")
    public PostDTO getPost(@PathVariable Long id){
        return userService.getComment(id).getPostDTO();
    }
    @GetMapping("/comments/{id}/post/content")
    public String getPostContent(@PathVariable Long id){
        return userService.getComment(id).getPostDTO().getContent();
    }
    @GetMapping("/comments/{id}/post_id")
    public Long getPostId(@PathVariable Long id){
        return userService.getComment(id).getPostDTO().getId();
    }
    @GetMapping("/comments/{id}/post_title")
    public String getPostTitle(@PathVariable Long id){
        return userService.getComment(id).getPostDTO().getTitle();
    }
    @GetMapping("/comments/{id}/post_created_date")
    public String getPostCreatedDate(@PathVariable Long id){
        return userService.getComment(id).getPostDTO().getCreatedDate().toString();
    }
    @GetMapping("/comments/{id}/post_patient")
    public PatientDTO getPostPatient(@PathVariable Long id){
        return userService.getComment(id).getPostDTO().getPatientDTO();
    }
    @GetMapping("/comments/{id}/post_therapist")
    public TherapistDTO getPostTherapist(@PathVariable Long id){
        return userService.getComment(id).getPostDTO().getTherapistDTO();
    }



    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable Long id){
        userService.DeleteComment(id);
    }

    @PostMapping("/comment")
    public CommentDTO addComment(@RequestBody CommentDTO commentDTO,@RequestParam Long postID){
        PostDTO postDTO = userService.getPost(postID);
        commentDTO.setPostDTO(postDTO);
        commentDTO.setCreatedDate(new Date());
        return userService.SaveComment(commentDTO,postID);
    }
    @PostMapping("/comment_pt/{postID}")
    public CommentDTO SaveComment(@PathVariable Long postID,@RequestBody CommentDTO commentDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        PatientDTO patientDTO = userService.findPatientByUsername(username);
        PostDTO postDTO = userService.getPost(postID);
        commentDTO.setPostDTO(postDTO);
        commentDTO.setPatientDTO(patientDTO);
        commentDTO.setCreatedDate(new Date());
        return userService.SaveComment(commentDTO,postDTO.getId());
    }

    @PatchMapping("/comments/{id}")
    public CommentDTO updateComment(@RequestBody CommentDTO commentDTO,@PathVariable Long id){
        commentDTO.setId(id);
        return userService.UpdateComment(commentDTO);
    }
}

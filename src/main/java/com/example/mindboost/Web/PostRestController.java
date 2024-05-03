package com.example.mindboost.Web;

import com.example.mindboost.DTOs.PostDTO;
import com.example.mindboost.Entities.Comment;
import com.example.mindboost.Entities.Patient;
import com.example.mindboost.Entities.Post;
import com.example.mindboost.Entities.Therapist;
import com.example.mindboost.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable(name = "id") Long Id){
    return userService.getPost(Id);
    }
    @GetMapping("/posts/{id}/patient")
    public Patient getPatient(@PathVariable(name = "id") Long Id){
        return userService.getPost(Id).getPatient();
    }
    @GetMapping("/posts/{id}/therapist")
    public Therapist getTherapist(@PathVariable(name = "id") Long Id){
        return userService.getPost(Id).getTherapist();
    }
    @GetMapping("/posts/{id}/comments")
    public List<Comment> getPostComments(@PathVariable(name = "id") Long postId){
        return userService.COMMENT_LIST_PERPost(postId);
    }
    @PostMapping("/posts")
    public PostDTO AddPost(@RequestBody PostDTO postDTO){
        return userService.SavePost(postDTO);
    }
    @DeleteMapping("/posts/{id}")
    public void DeletePost(@PathVariable(name = "id") Long id){
        userService.DeletePost(id);
    }

}

package com.example.mindboost.Web;

import com.example.mindboost.Entities.Comment;
import com.example.mindboost.Entities.Patient;
import com.example.mindboost.Entities.Post;
import com.example.mindboost.Entities.Therapist;
import com.example.mindboost.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class PostRestController {

    private UserService userService;

    @GetMapping("/posts")
    public List<Post> posts(){
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
    @GetMapping("/posts/patients")
    public List<Patient> getPatients(){
        return userService.PATIENT_LIST();
    }
    @GetMapping("/posts/therapsites")
    public List<Therapist> getTherapsites(){
        return userService.Therapist_LIST();
    }

}

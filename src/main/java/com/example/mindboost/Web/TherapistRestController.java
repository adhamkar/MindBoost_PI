package com.example.mindboost.Web;

import com.example.mindboost.DTOs.PatientDTO;
import com.example.mindboost.DTOs.PostDTO;
import com.example.mindboost.DTOs.TherapistDTO;
import com.example.mindboost.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class TherapistRestController {
    private UserService userService;

    @GetMapping("/therapistes")
    public List<TherapistDTO> therapistes(){
        return userService.Therapist_LIST();
    }

    @GetMapping("/therapistes/{id}")
    public TherapistDTO getPost(@PathVariable(name = "id") Long Id){
        return userService.getTherapist(Id);
    }
    @PostMapping("/therapistes")
    public TherapistDTO addtherapist(@Valid @RequestBody TherapistDTO therapistDTO){
        return userService.SaveTherapist(therapistDTO);
    }

    @DeleteMapping("/therapistes/{id}")
    public void Deletetherapist(@PathVariable Long id){
        userService.DeleteTherapist(id);
    }

    @GetMapping("/therapistes/{id}/posts")
    public List<PostDTO> Therapist_Posts(@PathVariable Long id){
        return userService.Post_List_PERTherapist(id);
    }


@PatchMapping("/therapistes/{id}")
public TherapistDTO updatetherapist(@PathVariable(name = "id") Long Id, @RequestBody TherapistDTO therapistDTO){
    therapistDTO.setId(Id);
    return userService.UpdateTherapist(therapistDTO);
}
}

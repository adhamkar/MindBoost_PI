package com.example.mindboost.Web;

import com.example.mindboost.DTOs.NotePadDTO;
import com.example.mindboost.DTOs.PatientDTO;
import com.example.mindboost.DTOs.PostDTO;
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
public class PatientRestController {
    private UserService userService;

    @GetMapping("/patients")
    public List<PatientDTO> getAllPatients(){
        return userService.PATIENT_LIST();
    }
    @GetMapping("/patients/{id}")
    public PatientDTO getpatient(@PathVariable(name = "id") Long patientId){
        return userService.getpatient(patientId);
    }

    @PostMapping("/patients")
    public PatientDTO addpatient(@Valid @RequestBody PatientDTO patientDTO){
        return userService.SavePatient(patientDTO);
    }
    @DeleteMapping("/patients/{id}")
    public void Deletepatient(@PathVariable Long id){
        userService.DeletePatient(id);
    }
    @GetMapping("/patients/{id}/posts")
    public List<PostDTO> Patient_Posts(@PathVariable Long id){
        return userService.Post_LIST_PERPatient(id);
    }
    @GetMapping("/patients/{id}/notepad")
    public List<NotePadDTO> Patient_NotePad(@PathVariable Long id){
        return userService.NotePad_LIST_PERPatient(id);
    }
    @PatchMapping("/patients/{id}")
    public PatientDTO UpdatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO){
        patientDTO.setId(id);
        return userService.UpdatePatient(patientDTO);
    }
}

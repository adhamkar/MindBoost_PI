package com.example.mindboost.Web;

import com.example.mindboost.DTOs.*;
import com.example.mindboost.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class NotepadRestController {
    private UserService userService;

    @GetMapping("/notes")
    public List<NotePadDTO> Notes(){
        return userService.NotePad_LIST();
    }

    @GetMapping("/notes/{id}")
    public NotePadDTO getPost(@PathVariable(name = "id") Long Id){
        return userService.getNotePad(Id);
    }

    @GetMapping("/notes/{id}/patient")
    public PatientDTO getPatient(@PathVariable(name = "id") Long Id){
        return userService.getNotePad(Id).getPatientDTO();
    }


    @PostMapping("/patient_note")
    public NotePadDTO AddNote(@RequestBody NotePadDTO notePadDTO,@RequestParam Long patientID){
        PatientDTO patientDTO = userService.getpatient(patientID);

        notePadDTO.setPatientDTO(patientDTO);
        notePadDTO.setCreatedDate(new Date());
        return userService.SaveNotePad(notePadDTO);
    }
    @DeleteMapping("/notes/{id}")
    public void DeleteNote(@PathVariable(name = "id") Long Id){
        userService.DeleteNotePad(Id);
    }

    @PatchMapping("/notes/{id}")
    public NotePadDTO updateNoteByPatient(@RequestBody NotePadDTO notePadDTO,@PathVariable Long id){
        notePadDTO.setId(id);
        return userService.UpdateNotePad(notePadDTO);

    }
}

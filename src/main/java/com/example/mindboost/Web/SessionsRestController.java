package com.example.mindboost.Web;

import com.example.mindboost.DTOs.PatientDTO;
import com.example.mindboost.DTOs.TherapieSessionDTO;
import com.example.mindboost.DTOs.TherapistDTO;
import com.example.mindboost.Entities.TherapieSession;
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
public class SessionsRestController {
    private UserService userService;

    @GetMapping("/sessions")
    public List<TherapieSessionDTO> sessions(){
        return userService.TherapieSession_LIST();
    }
    @GetMapping("/sessions/{idSession}")
    public TherapieSessionDTO getSession(@PathVariable(name = "idSession") Long Id){
        return userService.getTherapieSession(Id);
    }
    @GetMapping("/sessions/Patient/{patientId}")
    public List<TherapieSessionDTO> getSessionsByPatient(@PathVariable(name = "patientId") String patientId) {
        return userService.getSessionsByPatientId(patientId);
    }
    @GetMapping("/sessions/name/{sessionname}")
   public List<TherapieSessionDTO> SearchSessionByName(@PathVariable(name="sessionname") String name){
        return userService.SearchTherapieSession(name);
    }
    @GetMapping("/sessions/{id}/therapist")
    public TherapistDTO getTherapist(@PathVariable(name = "id") Long Id){
        return userService.getTherapieSession(Id).getTherapistDTO();
    }
    @GetMapping("/sessions/{id}/patient")
    public PatientDTO getPatient(@PathVariable(name = "id") Long Id){
        return userService.getTherapieSession(Id).getPatientDTO();
    }
    @GetMapping("/sessions/{id}/date")
    public TherapieSessionDTO getDate(@PathVariable(name = "id") Long Id){
        return userService.getTherapieSession(Id);
    }

    /*
    * @PostMapping("/patient_post")
   public PostDTO AddPostByPatient(@RequestBody PostDTO postDTO) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String username = authentication.getName();
      PatientDTO patientDTO = userService.findPatientByUsername(username);
      postDTO.setPatientDTO(patientDTO);
      postDTO.setUser_visibility(true);
      postDTO.setCreatedDate(new Date());
      return userService.savePost(postDTO);
  }*/

    @PostMapping("/sessions/{patieny_id}/{therapist_id}")
    public TherapieSessionDTO addSession(@RequestBody TherapieSessionDTO session,@PathVariable Long patieny_id,@PathVariable Long therapist_id){
        PatientDTO patientDTO = userService.getpatient(patieny_id);
        TherapistDTO therapistDTO = userService.getTherapist(therapist_id);

        if(therapistDTO.getAviability()){
            session.setPatientDTO(patientDTO);
            session.setTherapistDTO(therapistDTO);
            session.setDateSession(new Date());
            session.setTherapisteName(therapistDTO.getUserName());
            session.setPatientName(patientDTO.getUserName());
            return userService.SaveTherapieSession(session);
        }
        return null;
    }
    @PostMapping("/sessions/{patient_name}/{therapist_id}/mysession")
    public TherapieSessionDTO SessionReservation(@RequestBody TherapieSessionDTO session,@PathVariable String patient_name,@PathVariable Long therapist_id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        patient_name = authentication.getName();
        PatientDTO patientDTO = userService.findPatientByUsername(patient_name);
        TherapistDTO therapistDTO = userService.getTherapist(therapist_id);
        if(therapistDTO.getAviability()){
            session.setPatientDTO(patientDTO);
            session.setTherapistDTO(therapistDTO);
            //session.setDateSession(new Date());
            session.setTherapisteName(therapistDTO.getUserName());
            session.setPatientName(patientDTO.getUserName());
            return userService.SaveTherapieSession(session);
        }
        return null;
    }
    @GetMapping("/sessions/therapist/{therapist_name}")
    public List<TherapieSessionDTO> getSessionsByTherapist(@PathVariable(name = "therapist_name") String therapist_name) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        therapist_name = authentication.getName();
        return userService.getSessionsByTherapist(therapist_name);
    }
    @PatchMapping("/sessions/{id}")
    public TherapieSessionDTO updateSession(@PathVariable(name = "id") Long Id, @RequestBody TherapieSessionDTO session){
        session.setId(Id);
        return userService.UpdateTherapieSession(session);
    }
    @DeleteMapping("/sessions/{id}")
    public void deleteSession(@PathVariable(name = "id") Long Id){
        userService.DeleteTherapieSession(Id);
    }

}

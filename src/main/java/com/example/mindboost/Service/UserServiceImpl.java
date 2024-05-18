package com.example.mindboost.Service;

import com.example.mindboost.DTOs.*;
import com.example.mindboost.Entities.*;
import com.example.mindboost.Mappers.MapperImpl;
import com.example.mindboost.Repositories.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    /************** Repositories *************/
    private CommentRepo commentRepo;
    private PostRepo postRepo;
    private PatientRepo patientRepo;
    private TherapistRepo therapistRepo;
    private TherapySessionRepo therapySessionRepo;
    private NotepadRepo notepadRepo;
    private AdminRepo adminRepo;
    private MapperImpl mapper;
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    /************** Users implementation *************/
    @Override
    public UserDTO SaveUser(UserDTO userDTo) {
        User user = mapper.FromUserDTO(userDTo);
        User userSaved = userRepo.save(user);
        return mapper.FromUser(userSaved);
    }
    @Override
   public UserDTO LoadUserByUserName(String userName) {
        User user = userRepo.findByUserName(userName);
        UserDTO userDTO = mapper.FromUser(user);
        userDTO.setRole(user.getRole());
        return userDTO;
    }
    @Override
    public List<UserDTO> User_LIST() {
        List<User> users = userRepo.findAll();
        List<UserDTO> userDTOS = users.stream()
                .map(user -> mapper.FromUser(user))
                .collect(Collectors.toList());
        return userDTOS;
    }
    @Override
    public UserDTO getUser(Long userID) {
        User user = userRepo.findById(userID).orElseThrow(null);
        if (user != null) {
            return mapper.FromUser(user);
        }
        return null;
    }
    @Override
    public void DeleteUser(Long userID) {
        if (userRepo.findById(userID) != null) {
            userRepo.deleteById(userID);
        }
    }
    @Override
    public UserDTO UpdateUser(UserDTO userDTO) {
        User user = mapper.FromUserDTO(userDTO);
        List<User> allusers = userRepo.findAll();
        if (allusers.contains(user)) {
            User savedUser = userRepo.save(user);
            return mapper.FromUser(savedUser);
        }
        return null;
    }
    @Override
    public AdminDTO SaveAdmin(AdminDTO adminDTO) {
        Admin admin = mapper.FromAdminDTO(adminDTO);
        List<Admin> allAdmins = adminRepo.findAll();
        if (allAdmins.contains(admin)) {
            return null;
        }
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        Admin savedAdmin = adminRepo.save(admin);
        adminDTO.setId(savedAdmin.getId());
        return adminDTO;
    }
    @Override
    public AdminDTO UpdateAdmin(AdminDTO adminDTO) {
        Admin admin = mapper.FromAdminDTO(adminDTO);
        List<Admin> allAdmins = adminRepo.findAll();
        if (allAdmins.contains(admin)) {
            Admin savedAdmin = adminRepo.save(admin);
            return mapper.FromAdmin(savedAdmin);
        }
        return null;
    }
    @Override
    public List<AdminDTO> Admin_LIST() {
        List<Admin> admins = adminRepo.findAll();
        List<AdminDTO> adminDTOS = admins.stream()
                .map(admin -> mapper.FromAdmin(admin))
                .collect(Collectors.toList());
        return adminDTOS;
    }

    @Override
    public PatientDTO SavePatient(PatientDTO patientDTO) {
        Patient patient = mapper.FromPatientDTO(patientDTO);
        List<Patient> allpatients = patientRepo.findAll();

        if (allpatients.contains(patient)) {
            return null;
        }
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        Patient savedPatient = patientRepo.save(patient);
        patientDTO.setId(savedPatient.getId());
        return patientDTO;
    }


    @Override
    public TherapistDTO SaveTherapist(TherapistDTO therapistDTO) {
        Therapist therapist = mapper.FromTherapistDTO(therapistDTO);
        List<Therapist> alltherapist = therapistRepo.findAll();
        if (alltherapist.contains(therapist)) {
            return null;
        }
        therapist.setPassword(passwordEncoder.encode(therapist.getPassword()));
        Therapist savedTherapist = therapistRepo.save(therapist);
        therapistDTO.setId((savedTherapist.getId()));
        return therapistDTO;

    }

    @Override
    public PatientDTO UpdatePatient(PatientDTO patientDTO) {
        Patient patient = mapper.FromPatientDTO(patientDTO);
        List<Patient> allpatients = patientRepo.findAll();
        if (allpatients.contains(patient)) {
            Patient savedPatient = patientRepo.save(patient);
            return mapper.FromPatient(savedPatient);
        }
        return null;
    }

    @Override
    public TherapistDTO UpdateTherapist(TherapistDTO therapistDTO) {
        Therapist therapist = mapper.FromTherapistDTO(therapistDTO);
        List<Therapist> alltherapist = therapistRepo.findAll();
        if (alltherapist.contains(therapist)) {
            Therapist savedTherapist = therapistRepo.save(therapist);
            return mapper.FromTherapist(savedTherapist);
        }
        return null;
    }

    @Override
    public List<PatientDTO> PATIENT_LIST() {
        List<Patient> patients = patientRepo.findAll();
        List<PatientDTO> patientDTOS = patients.stream()
                .map(patient -> mapper.FromPatient(patient))
                .collect(Collectors.toList());
        return patientDTOS;
    }

    @Override
    public List<TherapistDTO> Therapist_LIST() {
        List<Therapist> therapistes = therapistRepo.findAll();
        List<TherapistDTO> therapistDTOS = therapistes.stream()
                .map(therapist -> mapper.FromTherapist(therapist))
                .collect(Collectors.toList());
        return therapistDTOS;
    }

    @Override
    public PatientDTO getpatient(Long patientID) {
        Patient patient = patientRepo.findById(patientID)
                .orElseThrow(() -> new RuntimeException("patient not found"));
        return mapper.FromPatient(patient);
    }

    @Override
    public TherapistDTO getTherapist(Long therapistID) {
        Therapist therapist = therapistRepo.findById(therapistID)
                .orElseThrow(() -> new RuntimeException("Therapist not found"));
        return mapper.FromTherapist(therapist);
    }

    @Override
    public void DeletePatient(Long patientID) {
        if (patientRepo.findById(patientID) != null) {
            patientRepo.deleteById(patientID);
        }
    }

    @Override
    public void DeleteTherapist(Long therapistID) {
        if (therapistRepo.findById(therapistID) != null) {
            patientRepo.deleteById(therapistID);
        }
    }

    @Override
    public List<PatientDTO> SearchPatient(String name) {
        List<Patient> patients = patientRepo.searchPatientByUserName(name);
        List<PatientDTO> patientDTOS = patients.stream()
                .map(patient -> mapper.FromPatient(patient))
                .collect(Collectors.toList());
        return patientDTOS;
    }

    @Override
    public List<TherapistDTO> SearchTherapist(String name) {
        List<Therapist> therapistes = therapistRepo.searchTherapistByUserName(name);
        List<TherapistDTO> therapistDTOS = therapistes.stream()
                .map(therapist -> mapper.FromTherapist(therapist))
                .collect(Collectors.toList());
        return therapistDTOS;
    }

    /************** Post implementation *************/
    @Override
    public PostDTO savePost(PostDTO postDTO) {
        PatientDTO patientDTO = postDTO.getPatientDTO();
        Patient patient = patientRepo.findById(patientDTO.getId()).orElse(null);
        if (patient == null) {
            log.info("Patient not found with ID: " + patientDTO.getId());
            return null;
        }
        Post post = mapper.FromPostDTO(postDTO);
        post.setPatient(patient);
        Post savedPost = postRepo.save(post);
        postDTO.setId(savedPost.getId());
        return mapper.FromPost(savedPost);
    }


    @Override
    public PostDTO savePost_Therapist(PostDTO postDTO) {
        TherapistDTO therapistDTO = postDTO.getTherapistDTO();
        Therapist therapist = therapistRepo.findById(therapistDTO.getId()).orElseThrow(null);
        if (therapist == null) {
            log.info("Therapist not found with ID: " + therapistDTO.getId());
            return null;
        }
        Post post = mapper.FromPostDTO(postDTO);
        post.setTherapist(therapist);
        Post savedPost = postRepo.save(post);
        postDTO.setId(savedPost.getId());
        return mapper.FromPost(savedPost);
    }

    @Override
    public PostDTO SavePost(PostDTO postDTO, Long Patient_id) {
        Patient pt = patientRepo.findById(Patient_id).orElse(null);
        if (pt == null)
            log.info("Patient not found with ID: " + Patient_id);
        Post post = mapper.FromPostDTO(postDTO);
        post.setPatient(pt);
        Post savedPost = postRepo.save(post);
        return mapper.FromPost(savedPost);
    }

    @Override
    public void DeletePost(Long postID) {
        if (postRepo.findById(postID) != null) {
            postRepo.deleteById(postID);
        }
        System.out.println("User's post not found to delete");
    }

    @Override
    public PostDTO UpdatePost(PostDTO postDTO) {

        if (postDTO.getId() == null) {
            throw new IllegalArgumentException("Post ID cannot be null for update operation.");
        }

        Post existingPost = postRepo.findById(postDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Post not found with ID: " + postDTO.getId()));
        existingPost.setContent(postDTO.getContent());
        existingPost.setUser_visibility(postDTO.getUser_visibility());
        existingPost.setUpdatedDate(new Date());

        Post updatedPost = postRepo.save(existingPost);
        return mapper.FromPost(updatedPost);
    }


    @Override
    public PostDTO getPost(Long postID) {
        Post post = postRepo.findById(postID).orElseThrow(null);
        if (post != null) {
            return mapper.FromPost(post);
        }
        return null;
    }

    @Override
    public List<PostDTO> Post_LIST_PERPatient(Long patientID) {
        Patient patient = patientRepo.findById(patientID).orElseThrow(null);
        if (patient == null) {
            return null;
        }
        List<Post> patientPosts = postRepo.findAllByPatientId(patientID);
        List<PostDTO> postDTOS = patientPosts.stream()
                .map(post -> mapper.FromPost(post))
                .collect(Collectors.toList());
        return postDTOS;
    }

    @Override
    public List<PostDTO> Post_List_PERTherapist(Long therapistID) {
        Therapist therapist = therapistRepo.findById(therapistID).orElseThrow(null);
        if (therapist == null) {
            return null;
        }
        List<Post> therapistPosts = postRepo.findAllByTherapistId(therapistID);
        List<PostDTO> postDTOS = therapistPosts.stream()
                .map(post -> mapper.FromPost(post))
                .collect(Collectors.toList());
        return postDTOS;
    }

    @Override
    public List<PostDTO> Post_LIST() {
        List<Post> posts = postRepo.findAll();
        List<PostDTO> postDTOS = posts.stream()
                .map(post -> mapper.FromPost(post))
                .collect(Collectors.toList());
        return postDTOS;
    }

    /************** Comments implementation *************/

    @Override
    public CommentDTO SaveComment(CommentDTO commentDTO, Long Post_id) {
        Post post = postRepo.findById(Post_id).orElseThrow(null);
        if (commentDTO == null) {
            return null;
        }
        Comment comment = mapper.FromCommentDTO(commentDTO);
        comment.setPost(post);
        comment.setTherapist(post.getTherapist());
        comment.setPatient(post.getPatient());
        Comment savedComment = commentRepo.save(comment);
        return mapper.FromComment(savedComment);
    }

    @Override
    public void DeleteComment(Long commentID) {
        commentRepo.deleteById(commentID);
    }

    @Override
    public CommentDTO UpdateComment(CommentDTO commentDTO) {
        if (commentDTO.getId() == null) {
            throw new IllegalArgumentException("Comment ID cannot be null for update operation.");
        }
        Comment existingComment = commentRepo.findById(commentDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with ID: " + commentDTO.getId()));
        existingComment.setComment(commentDTO.getComment());
        existingComment.setCreatedDate(new Date());
        Comment updatedComment = commentRepo.save(existingComment);
        return mapper.FromComment(updatedComment);
    }

    @Override
    public CommentDTO getComment(Long commentID) {
        /*return commentRepo.findById(commentID).orElseThrow(() ->
                new RuntimeException("comment not found"));*/
        Comment comment = commentRepo
                .findById(commentID)
                .orElseThrow(() -> new RuntimeException("comment not found"));
        return mapper.FromComment(comment);
    }

    @Override
    public List<CommentDTO> Comment_LIST() {

        List<Comment> comments = commentRepo.findAll();
        List<CommentDTO> commentDTOS = comments.stream()
                .map(comment -> mapper.FromComment(comment))
                .collect(Collectors.toList());
        return commentDTOS;
    }

    @Override
    public List<CommentDTO> COMMENT_LIST_PERPost(Long postId) {
        /*
        List<Comment> comments=commentRepo.findAll();
        List<CommentDTO> commentDTOS=comments.stream()
                .map(comment -> mapper.FromComment(comment))
                .collect(Collectors.toList());
         */
        Post post = postRepo.findById(postId).orElseThrow(null);
        List<Comment> postComments = commentRepo.findCommentsByPost(post);
        List<CommentDTO> commentDTOS = postComments.stream()
                .map(comment -> mapper.FromComment(comment))
                .collect(Collectors.toList());
        return commentDTOS;
    }

    /************** NotePad implementation *************/
    @Override
    public NotePadDTO SaveNotePad(NotePadDTO notePadDTO) {
        PatientDTO patientDTO = notePadDTO.getPatientDTO();
        Patient patient = patientRepo.findById(patientDTO.getId()).orElse(null);
        if (patient == null) {
            log.info("Patient not found with ID: " + patientDTO.getId());
            return null;
        }
        NotePad notePad = mapper.FromNotePadDTO(notePadDTO);
        notePad.setPatient(patient);
        NotePad savedNotePad = notepadRepo.save(notePad);
        notePadDTO.setId(savedNotePad.getId());
        return mapper.FromNotePad(savedNotePad);
    }

    @Override
    public void DeleteNotePad(Long notePadID) {
        if(notepadRepo.findById(notePadID) != null){
            notepadRepo.deleteById(notePadID);
        }
    }
    @Override
    public NotePadDTO UpdateNotePad(NotePadDTO notePadDTO) {
        if (notePadDTO.getId() == null) {
            throw new IllegalArgumentException("NotePad ID cannot be null for update operation.");
        }
        NotePad existingNotePad = notepadRepo.findById(notePadDTO.getId())
                .orElseThrow(() ->
                        new IllegalArgumentException("NotePad not found with ID: " + notePadDTO.getId()));
        existingNotePad.setTitle(notePadDTO.getTitle());
        existingNotePad.setContent(notePadDTO.getContent());
        existingNotePad.setModifieddDate(new Date());
        NotePad updatedNotePad = notepadRepo.save(existingNotePad);
        return mapper.FromNotePad(updatedNotePad);
    }
    @Override
    public NotePadDTO getNotePad(Long notePadID) {
        NotePad notePad = notepadRepo.findById(notePadID).orElseThrow(null);
        if (notePad != null) {
            return mapper.FromNotePad(notePad);
        }
        return null;
    }

    @Override
    public List<NotePadDTO> NotePad_LIST() {
        List<NotePad> notePads = notepadRepo.findAll();
        List<NotePadDTO> notePadDTOS = notePads.stream()
                .map(notePad -> mapper.FromNotePad(notePad))
                .collect(Collectors.toList());
        return notePadDTOS;
    }

    @Override
    public List<NotePad> SearchNotePad(String title) {
        return notepadRepo.searchByTitle(title);
    }

    /************** TherapieSession implementation *************/
    @Override
    public TherapieSessionDTO SaveTherapieSession(TherapieSessionDTO therapieSessionDTO) {
        PatientDTO patientDTO = therapieSessionDTO.getPatientDTO();
        Patient patient = patientRepo.findById(patientDTO.getId()).orElse(null);

        TherapistDTO therapistDTO = therapieSessionDTO.getTherapistDTO();
        Therapist therapist = therapistRepo.findById(therapistDTO.getId()).orElseThrow(null);

        if (patient == null || therapist == null) {
            return null;
        }

        TherapieSession therapieSession = mapper.FromTherapySessionDTO(therapieSessionDTO);
        therapieSession.setPatient(patient);
        therapieSession.setTherapist(therapist);
        TherapieSession SavedSession = therapySessionRepo.save(therapieSession);
        return mapper.FromTherapySession(SavedSession);
    }

    @Override
    public void DeleteTherapieSession(Long therapieSessionID) {
        if (therapySessionRepo.findById(therapieSessionID).isPresent()) {
            therapySessionRepo.deleteById(therapieSessionID);
        }
    }

    @Override
    public TherapieSessionDTO UpdateTherapieSession(TherapieSessionDTO therapieSession) {
       /* List<TherapieSession> sessions = therapySessionRepo.findAll();
        if (sessions.contains(therapieSession)) {
            therapySessionRepo.save(therapieSession);
        }
        return null;*/
        if (therapieSession.getId() == null) {
            throw new IllegalArgumentException("TherapieSession ID cannot be null for update operation.");
        }
        TherapieSession existingSession = therapySessionRepo.findById(therapieSession.getId())
                .orElseThrow(() ->
                        new IllegalArgumentException("TherapieSession not found with ID: " + therapieSession.getId()));
        existingSession.setNameSession(therapieSession.getNameSession());
        existingSession.setPatientName(therapieSession.getPatientName());
        existingSession.setTherapisteName(therapieSession.getTherapisteName());

        TherapieSession updatedSession = therapySessionRepo.save(existingSession);
        return mapper.FromTherapySession(updatedSession);
    }

    @Override
    public TherapieSessionDTO getTherapieSession(Long therapieSessionID) {
        TherapieSession therapieSession = therapySessionRepo.findById(therapieSessionID).orElseThrow(null);
        if (therapieSession != null) {
            return mapper.FromTherapySession(therapieSession);
        }
        return null;
    }

    @Override
    public List<TherapieSessionDTO> TherapieSession_LIST() {
        List<TherapieSession> therapieSessions = therapySessionRepo.findAll();
        List<TherapieSessionDTO> therapieSessionDTOS = therapieSessions.stream()
                .map(therapieSession -> mapper.FromTherapySession(therapieSession))
                .collect(Collectors.toList());
        return therapieSessionDTOS;
    }

    @Override
    public List<TherapieSessionDTO> SearchTherapieSession(String SessionName) {
        List<TherapieSession> therapieSessions = therapySessionRepo.searchByNameSession(SessionName);
        List<TherapieSessionDTO> therapieSessionDTOS = therapieSessions.stream()
                .map(therapieSession -> mapper.FromTherapySession(therapieSession))
                .collect(Collectors.toList());
        return therapieSessionDTOS;
    }

    @Override
    public List<NotePadDTO> NotePad_LIST_PERPatient(Long patientID) {
        Patient patient = patientRepo.findById(patientID).orElseThrow(null);
        if (patient == null) {
            return null;
        }
        List<NotePad> notePads = notepadRepo.findAllByPatientId(patientID);
        List<NotePadDTO> notePadDTOS = notePads.stream()
                .map(notePad -> mapper.FromNotePad(notePad))
                .collect(Collectors.toList());
        return notePadDTOS;
    }

    @Override
    public PatientDTO findPatientByUsername(String username) {
        Patient patient = patientRepo.findByUserName(username);
        return mapper.FromPatient(patient);
    }


}

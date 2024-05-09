package com.example.mindboost.Repositories;

import com.example.mindboost.Entities.Patient;
import com.example.mindboost.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long> {
    @Query(value="SELECT * FROM post ORDER BY CreatedDate DESC",nativeQuery = true)
    List<Post> getAllPosts();
    //List<Post> getAllByCreatedDateOrderByCreatedDate();
    @Query(value = "SELECT p.patient FROM Post p WHERE p.id = :postId")
    Patient getPatientByPostId(@Param("postId") Long postId);
    Post findPostByPatientId(Long id);
    List<Post> findAllByPatientId(Long patientID);

    List<Post> findAllByTherapistId(Long therapistID);

}

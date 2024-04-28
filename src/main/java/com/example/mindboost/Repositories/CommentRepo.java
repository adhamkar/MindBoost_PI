package com.example.mindboost.Repositories;

import com.example.mindboost.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Long> {
    /*@Query(value="SELECT * FROM notepad ORDER BY createddate DESC",nativeQuery = true)
    List<Comment> GetAllComments();*/
}

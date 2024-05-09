package com.example.mindboost.Repositories;

import com.example.mindboost.Entities.Comment;
import com.example.mindboost.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Long> {
    /*@Query(value="SELECT * FROM notepad ORDER BY createddate DESC",nativeQuery = true)
    List<Comment> GetAllComments();*/
    List<Comment> findCommentsByPost(Post post);
    List<Comment> findCommentsByPostId(Long postID);
}

package com.example.mindboost.Repositories;

import com.example.mindboost.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long> {
    @Query(value="SELECT * FROM post ORDER BY CreatedDate DESC",nativeQuery = true)
    List<Post> getAllPosts();
    //List<Post> getAllByCreatedDateOrderByCreatedDate();
}

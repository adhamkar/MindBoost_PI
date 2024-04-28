package com.example.mindboost;

import com.example.mindboost.Entities.Comment;
import com.example.mindboost.Entities.NotePad;
import com.example.mindboost.Entities.Post;
import com.example.mindboost.Entities.User;
import com.example.mindboost.Enums.Gender;
import com.example.mindboost.Enums.Role;
import com.example.mindboost.Repositories.CommentRepo;
import com.example.mindboost.Repositories.NotepadRepo;
import com.example.mindboost.Repositories.PostRepo;
import com.example.mindboost.Repositories.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

@SpringBootApplication
public class MindBoostApplication {

    public static void main(String[] args) {
        SpringApplication.run(MindBoostApplication.class, args);
    }



    @Bean
    CommandLineRunner start(UserRepo userRepo,
                            PostRepo postRepo,
                            CommentRepo commentRepo,
                            NotepadRepo notepadRepo){
        Random random=new Random();
        Gender[] genders = Gender.values();
        Role[] roles=Role.values();
        return args -> {
            Stream.of("Adham","Ikram","Simo").forEach(name->{
                User user=new User();
                user.setUserName(name);
                user.setEmail(name+"@gmail.com");
                int i = random.nextInt(genders.length);
                user.setGender(genders[i]);
                int r = random.nextInt(roles.length);
                user.setRole(roles[r]);
                user.setPassword(name+random.nextInt(1000)+1);
                userRepo.save(user);
            });
            userRepo.findAll().forEach(user -> {
                Post post=new Post();
                post.setUser(user);
                post.setCreatedDate(new Date());
                post.setContent("this is "+user.getUserName()+"'s post");
                post.setUser_visibility(random.nextBoolean());
                postRepo.save(post);
            });
            postRepo.findAll().forEach(post -> {
                Comment comment=new Comment();
                comment.setPost(post);
                comment.setUser(post.getUser());
                comment.setComment("this is "+post.getUser().getUserName()+"'s comment");
                comment.setCreatedDate(new Date());
                commentRepo.save(comment);
            });
            userRepo.findAll().forEach(user -> {
                NotePad notePad=new NotePad();
                notePad.setUser(user);
                notePad.setContent("this is "+user.getUserName()+"'s notes");
                notePad.setCreatedDate(new Date());
                notePad.setTitle(user.getUserName()+"'s title");
                notepadRepo.save(notePad);
            });

        };
    }
}

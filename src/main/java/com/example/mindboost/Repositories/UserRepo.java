package com.example.mindboost.Repositories;

import com.example.mindboost.DTOs.UserDTO;
import com.example.mindboost.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUserName(String userName);

}

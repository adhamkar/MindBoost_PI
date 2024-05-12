package com.example.mindboost.Security.Repositories;

import com.example.mindboost.Security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByUserName(String userName);
    AppUser findByEmail(String email);
}

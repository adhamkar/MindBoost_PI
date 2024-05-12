package com.example.mindboost.Repositories;

import com.example.mindboost.Entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepo extends JpaRepository<Admin,Long> {
    List<Admin> searchAdminByUserName(String userName);
}

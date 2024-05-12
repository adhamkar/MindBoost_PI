package com.example.mindboost.Security.Repositories;

import com.example.mindboost.Security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepo extends JpaRepository<AppRole, String> {
    AppRole findByRole(String role);
}

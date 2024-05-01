package com.example.mindboost.Repositories;

import com.example.mindboost.Entities.TherapieSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TherapySessionRepo extends JpaRepository<TherapieSession,Long> {
    List<TherapieSession> searchTherapieSessionByNameSession(String name);
}

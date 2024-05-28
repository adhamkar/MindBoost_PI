package com.example.mindboost.Repositories;

import com.example.mindboost.Entities.Patient;
import com.example.mindboost.Entities.Therapist;
import com.example.mindboost.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TherapistRepo extends JpaRepository<Therapist,Long> {
    List<Therapist> searchTherapistByUserName(String name);

    Therapist findByUserName(String therapistName);
}

package com.example.mindboost.Repositories;

import com.example.mindboost.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepo extends JpaRepository<Patient,Long> {
    /*@Query("select p from Patient p where p.UserName like :kw")
    List<Patient> searchPatient(@Param("kw") String keyword);*/
    List<Patient> searchPatientByUserName(String userName);
}

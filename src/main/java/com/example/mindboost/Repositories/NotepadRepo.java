package com.example.mindboost.Repositories;

import com.example.mindboost.Entities.NotePad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotepadRepo extends JpaRepository<NotePad,Long> {
    /*@Query(value="SELECT * FROM notepad ORDER BY createddate DESC",nativeQuery = true)
    List<NotePad> GetAllNotes();*/
}

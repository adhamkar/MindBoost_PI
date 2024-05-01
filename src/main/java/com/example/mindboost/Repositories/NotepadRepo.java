package com.example.mindboost.Repositories;

import com.example.mindboost.Entities.NotePad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotepadRepo extends JpaRepository<NotePad,Long> {
   /* @Query(value="SELECT * FROM NotePad ORDER BY CreatedDate DESC", nativeQuery=true)
    List<NotePad> getAllNotes();*/

    @Query("select n from NotePad n where n.Title like :kw")
    List<NotePad> searchByTitle(@Param("kw") String keyword);

    //List<String> findNotePadsByTitle();
}

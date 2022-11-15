package com.example.geniusquizz.repository;

import com.example.geniusquizz.model.Question;
import com.example.geniusquizz.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {

    @Query(value = "DELETE FROM sessions_questions WHERE question_id = ?1 ;", nativeQuery = true)
    public void deleteQuestionInSessions(Long id);


}

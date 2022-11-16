package com.example.geniusquizz.repository;

import com.example.geniusquizz.model.Question;
import com.example.geniusquizz.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {

    @Query(value = "DELETE FROM sessions_questions WHERE question_id = ?1 ;", nativeQuery = true)
    public void deleteQuestionInSessions(Long id);

    @Query(value = "SELECT * FROM session WHERE user_id = ?1 ;", nativeQuery = true)
    public Set<Session> getAllByUser(Long id);

    @Query(value = "SELECT * FROM session WHERE user_id = ?1 and life > 0 LIMIT 6;", nativeQuery = true)
    public Set<Session> getAllByUserLimit6(Long id);
}

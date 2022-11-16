package com.example.geniusquizz.repository;

import com.example.geniusquizz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    Question findQuestionById (Long id);

    @Query(value = "select * from question where id not in " +
            "(select question_id from sessions_questions where session_id = ?1) " +
            "ORDER BY RAND() LIMIT 1;", nativeQuery = true)
    public Question findQuestionNotInSession(Long id);
}

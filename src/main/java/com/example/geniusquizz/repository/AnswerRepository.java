package com.example.geniusquizz.repository;

import com.example.geniusquizz.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {
    @Query(value = "select * from answer where question_id = ?1", nativeQuery = true)
    public Collection<Answer> findAnswerByQuestion(Long id);

    @Query(value = "select * from answer where question_id = ?1 and right_answer = true", nativeQuery = true)
    public Answer findGoodAnswerByQuestion(Long id);
}

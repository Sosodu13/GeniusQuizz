package com.example.geniusquizz.repository;

import com.example.geniusquizz.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
}

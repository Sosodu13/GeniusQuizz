package com.example.geniusquizz.repository;

import com.example.geniusquizz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}

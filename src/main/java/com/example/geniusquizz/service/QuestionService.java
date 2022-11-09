package com.example.geniusquizz.service;

import com.example.geniusquizz.model.Question;
import java.util.Collection;

public interface QuestionService {
    Collection<Question> getAll();
    Question findById(Long id);
    Question delete(Long id);
    Question save(Question question);
}

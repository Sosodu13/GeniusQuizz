package com.example.geniusquizz.service;

import com.example.geniusquizz.model.Answer;
import java.util.Collection;

public interface AnswerService {
    Collection<Answer> getAll();
    Answer findById(Long id);
    Answer delete(Long id);
    Answer save(Answer answer);
    Collection<Answer> saveAll(Collection<Answer> answerList);
}

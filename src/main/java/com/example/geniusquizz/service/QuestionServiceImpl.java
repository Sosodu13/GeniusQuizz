package com.example.geniusquizz.service;

import com.example.geniusquizz.model.Question;
import com.example.geniusquizz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Collection<Question> getAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question findById(Long id) {
        return questionRepository.findById(id).get();
    }

    @Override
    public Question delete(Long id) {
        Question question = findById(id);
        questionRepository.deleteById(id);
        return question;
    }

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }
}

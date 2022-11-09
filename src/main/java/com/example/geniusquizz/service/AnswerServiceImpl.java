package com.example.geniusquizz.service;

import com.example.geniusquizz.model.Answer;
import com.example.geniusquizz.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Collection<Answer> getAll() {
        return answerRepository.findAll();
    }

    @Override
    public Answer findById(Long id) {
        return answerRepository.findById(id).get();
    }

    @Override
    public Answer delete(Long id) {
        Answer answer = findById(id);
        answerRepository.deleteById(id);
        return answer;
    }

    @Override
    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public Collection<Answer> saveAll(Collection<Answer> answerList) {
        return answerRepository.saveAll(answerList);
    }
}

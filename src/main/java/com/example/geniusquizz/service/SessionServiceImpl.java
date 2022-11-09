package com.example.geniusquizz.service;

import com.example.geniusquizz.model.Session;
import com.example.geniusquizz.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class SessionServiceImpl implements SessionService{
    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public Collection<Session> getAll() {
        return sessionRepository.findAll();
    }

    @Override
    public Session findById(Long id) {
        return sessionRepository.findById(id).get();
    }

    @Override
    public Session delete(Long id) {
        Session session = findById(id);
        sessionRepository.deleteById(id);
        return session;
    }

    @Override
    public Session save(Session exam) {
        return sessionRepository.save(exam);
    }
}

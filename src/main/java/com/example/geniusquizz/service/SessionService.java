package com.example.geniusquizz.service;

import com.example.geniusquizz.model.Session;
import java.util.Collection;

public interface SessionService {
    Collection<Session> getAll();
    Session findById(Long id);
    Session delete(Long id);
    Session save(Session t);
}

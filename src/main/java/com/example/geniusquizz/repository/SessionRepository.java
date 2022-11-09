package com.example.geniusquizz.repository;

import com.example.geniusquizz.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session,Long> {
}

package com.example.geniusquizz.repository;

import com.example.geniusquizz.model.Question;
import com.example.geniusquizz.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {

}

package com.example.geniusquizz.repository;

import com.example.geniusquizz.model.Session;
import com.example.geniusquizz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    @Query(value = "select user.email,sum(session.score) from user INNER JOIN session on user.id = session.user_id group by 1 ORDER BY sum(session.score) DESC LIMIT 3", nativeQuery = true)
    public Object[] findScoreSessionsByUser();
}

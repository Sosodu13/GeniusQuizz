package com.example.geniusquizz.repository;

import com.example.geniusquizz.model.Role;
import com.example.geniusquizz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

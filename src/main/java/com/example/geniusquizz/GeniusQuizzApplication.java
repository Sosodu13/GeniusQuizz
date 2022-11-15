package com.example.geniusquizz;

import com.example.geniusquizz.model.Role;
import com.example.geniusquizz.model.User;
import com.example.geniusquizz.repository.RoleRepository;
import com.example.geniusquizz.repository.UserRepository;
import com.example.geniusquizz.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootApplication
public class GeniusQuizzApplication {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(GeniusQuizzApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            if (roleRepository.findAll().size() == 0)
            {
                Role role = new Role("ROLE_USER");
                Role role1 = new Role("SUPER_ADMIN");
                roleRepository.save(role);
                roleRepository.save(role1);
            }

        };
    }
}

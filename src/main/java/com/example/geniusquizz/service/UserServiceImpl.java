package com.example.geniusquizz.service;

import com.example.geniusquizz.model.Role;
import com.example.geniusquizz.model.User;
import com.example.geniusquizz.repository.UserRepository;
import com.example.geniusquizz.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getFirstName(),
                registrationDto.getLastName(),
                registrationDto.getEmail(),
                registrationDto.getPassword(),
                List.of(new Role("ROLE_USER")));

        return userRepository.save(user);

    }
}

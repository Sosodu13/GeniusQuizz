package com.example.geniusquizz.service;

import com.example.geniusquizz.model.User;
import com.example.geniusquizz.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save (UserRegistrationDto registrationDto);

}

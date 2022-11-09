package com.example.geniusquizz.service;

import com.example.geniusquizz.model.User;
import com.example.geniusquizz.web.dto.UserRegistrationDto;

public interface UserService {

    User save (UserRegistrationDto registrationDto);

}

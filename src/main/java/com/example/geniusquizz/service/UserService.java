package com.example.geniusquizz.service;

import com.example.geniusquizz.model.User;
import com.example.geniusquizz.web.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    User save (UserDto userDto);
    UserDto getByEmail(String email);
//    UserDto getUserById(Long userId);
    UserDto updateAccount(UserDto userDTO);

}

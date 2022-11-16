package com.example.geniusquizz.service;

import com.example.geniusquizz.model.Role;
import com.example.geniusquizz.model.Session;
import com.example.geniusquizz.model.User;
import com.example.geniusquizz.repository.RoleRepository;
import com.example.geniusquizz.repository.UserRepository;
import com.example.geniusquizz.web.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User save(UserDto registrationDto) {

        Role role = roleRepository.findByName("ROLE_USER");
        Role roleFind;
        if (role == null) {

            roleFind = new Role("ROLE_USER");

        } else {
            roleFind = role;
        }

        User user = new User(registrationDto.getFirstName(),
                registrationDto.getLastName(),
                registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()),
                Set.of(roleFind));

        return userRepository.save(user);

    }

    @Override
    public UserDto getByEmail(String email) {
        User userEntity = userRepository.findByEmail(email);
        if (userEntity == null)
        {
            throw new UsernameNotFoundException(email);
        }
        UserDto returnUserDTO = new UserDto();
        BeanUtils.copyProperties(userEntity, returnUserDTO);
        return returnUserDTO;
    }

//    @Override
//    public UserDto getUserById(Long userId) {
//        UserDto returnUserDTO = new UserDto();
//        User userEntityByUserId = userRepository.findUserById(userId);
//
//        if (userEntityByUserId == null)
//        {
//            throw new UsernameNotFoundException("Utilisateur introuvable !!!!");
//        }
//
//        BeanUtils.copyProperties(userEntityByUserId, returnUserDTO);
//
//        return returnUserDTO;
//    }

    @Override
    public UserDto updateAccount(UserDto userDTO){
        User userEntityByUserId = userRepository.findByEmail(userDTO.getEmail());

        if (userEntityByUserId == null)
        {
            throw new UsernameNotFoundException("Utilisateur introuvable !!!!!!");
        }

        userEntityByUserId.setFirstName(userDTO.getFirstName());
        userEntityByUserId.setLastName(userDTO.getLastName());
        userEntityByUserId.setEmail(userDTO.getEmail());
        if (userDTO.getGithubInfo() == null)
        {
            userEntityByUserId.setGithubInfo(null);
        }else{
            userEntityByUserId.setGithubInfo(userDTO.getGithubInfo());
        }

        User updatedUserEntity = userRepository.save(userEntityByUserId);
        BeanUtils.copyProperties(updatedUserEntity, userDTO);

        return userDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("Email ou mot de passe incorrect !");
        }
//        return new GeniusQuizzUserDetails(user);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}

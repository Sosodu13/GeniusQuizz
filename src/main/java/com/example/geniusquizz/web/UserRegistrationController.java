package com.example.geniusquizz.web;

import com.example.geniusquizz.service.UserService;
import com.example.geniusquizz.web.dto.UserDto;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/inscription")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }


    @ModelAttribute("user")
    public UserDto userRegistrationDto(){
        return new UserDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(!(auth instanceof AnonymousAuthenticationToken))
        {
            return "redirect:/";
        }

        return "registration";
    }

    @PostMapping
    public String registrationUserAccount(@ModelAttribute("user") UserDto userDto, @RequestParam("formRPassword") String confirmPwd){

        if (!Objects.equals(userDto.getPassword(), confirmPwd))
        {
            return "redirect:/inscription?error";
        }
        if (userService.existUserByEmail(userDto.getEmail()))
        {
            return "redirect:/inscription?exists";
        }
        userService.save(userDto);
        return "redirect:/inscription?success";
    }
}

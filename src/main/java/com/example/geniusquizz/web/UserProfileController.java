package com.example.geniusquizz.web;

import com.example.geniusquizz.config.GeniusQuizzUserDetails;
import com.example.geniusquizz.model.User;
import com.example.geniusquizz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserProfileController {

    @Autowired
    private UserService service;

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal GeniusQuizzUserDetails loggedUser, Model model){


        String email = loggedUser.getUsername();
        User user = service.getByEmail(email);
        model.addAttribute("user", user);

        return "profile";


    }
}
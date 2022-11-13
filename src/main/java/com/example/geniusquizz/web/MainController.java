package com.example.geniusquizz.web;

import com.example.geniusquizz.model.User;
import com.example.geniusquizz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model, Principal principal){

        if(principal.getName() == null)
        {
            return "redirect:/login";
        }
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));

        return "index";
    }
}
package com.example.geniusquizz.web;

import com.example.geniusquizz.model.Session;
import com.example.geniusquizz.model.User;
import com.example.geniusquizz.repository.SessionRepository;
import com.example.geniusquizz.repository.UserRepository;
import com.example.geniusquizz.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collection;

@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionService sessionService;

    @GetMapping("/")
    public String home(Model model, Principal principal){

        if(principal.getName() == null)
        {
            return "redirect:/login";
        }
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));

        model.addAttribute("sessions", sessionService.getAll());

        return "index";
    }
}
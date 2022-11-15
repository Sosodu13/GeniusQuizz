package com.example.geniusquizz.web;

import com.example.geniusquizz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class FinishController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/finish")
    public String finish(Model model, Principal principal) {
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        return "finish";
    }
}

package com.example.geniusquizz.web;

import com.example.geniusquizz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/questions")
    public String home(Model model, Principal principal){

        if(principal.getName() == null)
        {
            return "redirect:/login";
        }

        boolean hasUserRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("SUPER_ADMIN"));

        if (!hasUserRole)
        {
            return "redirect:/";
        }
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));

        return "admin/questions";
    }

}

package com.example.geniusquizz.web;

import com.example.geniusquizz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class GameOverController {
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/gameover")
    public String gameover(Model model, Principal principal) {
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        if(httpSession.getAttribute("session_quizz") == null){
            return "redirect:/";
        }
        return "gameover";
    }
}

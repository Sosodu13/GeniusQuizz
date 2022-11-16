package com.example.geniusquizz.web;

import com.example.geniusquizz.model.User;
import com.example.geniusquizz.repository.SessionRepository;
import com.example.geniusquizz.repository.UserRepository;
import com.example.geniusquizz.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;

@Controller
public class MainController {
    @Autowired
    private HttpSession httpSession;
    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionService sessionService;

    @Autowired
    SessionRepository sessionRepository;


    @GetMapping("/")
    public String home(Model model, Principal principal){
        httpSession.removeAttribute("session_quizz");
        httpSession.removeAttribute("is_correct");
        httpSession.removeAttribute("correct_answer");
        if(principal.getName() == null)
        {
            return "redirect:/login";
        }
        User user = userRepository.findByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("sessions", sessionRepository.getAllByUserLimit6(user.getId()));

        Object[] o = userRepository.findScoreSessionsByUser();

        if (!indexExists(o,0) || !indexExists(o,1) || !indexExists(o,2)){
            model.addAttribute("podium",false);
            return "index";
        }

        String top1 = Arrays.deepToString(new Object[]{o[0]});
        String top2 = Arrays.deepToString(new Object[]{o[1]});
        String top3 = Arrays.deepToString(new Object[]{o[2]});

        top1 = top1.replace("[","");
        top1 = top1.replace("]","");
        String[] words1 = top1.split(",");
        model.addAttribute("top1Email", words1[0]);
        model.addAttribute("top1Score", words1[1]);

        top2 = top2.replace("[","");
        top2 = top2.replace("]","");
        String[] words2 = top2.split(",");
        model.addAttribute("top2Email", words2[0]);
        model.addAttribute("top2Score", words2[1]);

        top3 = top3.replace("[","");
        top3 = top3.replace("]","");
        String[] words3 = top3.split(",");
        model.addAttribute("top3Email", words3[0]);
        model.addAttribute("top3Score", words3[1]);
        model.addAttribute("podium",true);

        return "index";
    }

    public boolean indexExists(final Object[] list, final int index) {
        return index >= 0 && index < list.length;
    }
}
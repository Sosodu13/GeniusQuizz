package com.example.geniusquizz.web;

import com.example.geniusquizz.config.GeniusQuizzUserDetails;
import com.example.geniusquizz.model.User;
import com.example.geniusquizz.repository.UserRepository;
import com.example.geniusquizz.service.UserService;
import com.example.geniusquizz.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class UserProfileController {
    @Autowired
    private HttpSession httpSession;
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("")
    public String profile(Model model, Principal principal){
        httpSession.removeAttribute("session_quizz");
        httpSession.removeAttribute("is_correct");
        httpSession.removeAttribute("correct_answer");

        if(principal.getName() == null)
        {
            return "redirect:/login";
        }

        boolean hasUserRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("SUPER_ADMIN"));

        if (hasUserRole)
        {
            model.addAttribute("admin", true);
        }

        model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        return "profile";
    }

    @PostMapping("/update")
    public String saveDetails(UserDto user){

        if ((user.getFirstName() == null) ||
                (user.getLastName() == null) ||
                (user.getEmail() == null) ||
                (user.getFirstName().isEmpty()) ||
                (user.getLastName().isEmpty()) ||
                (user.getEmail().isEmpty()))
        {
            return "redirect:/profile?error";
        }

        Optional<User> userFind = userRepository.findById(user.getId());

        if (!Objects.equals(userFind.get().getEmail(), user.getEmail()))
        {
            return "redirect:/profile?changeEmail";
        }

        userService.updateAccount(user);

        return "redirect:/profile?success";
    }

    @GetMapping("/users")
    public String usersProfile(Model model, Principal principal){
        model.addAttribute("userConnected", userRepository.findByEmail(principal.getName()));
        model.addAttribute("users", userRepository.findAll());
        return "usersProfile";
    }

}
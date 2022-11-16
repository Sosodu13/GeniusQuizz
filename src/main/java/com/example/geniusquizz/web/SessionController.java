package com.example.geniusquizz.web;

import com.example.geniusquizz.model.Question;
import com.example.geniusquizz.model.Session;
import com.example.geniusquizz.model.User;
import com.example.geniusquizz.repository.SessionRepository;
import com.example.geniusquizz.repository.UserRepository;
import com.example.geniusquizz.service.SessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;

@Controller
public class SessionController {
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private SessionServiceImpl sessionService;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/sessions")
    public String sessions(Model model, Principal principal) {
        httpSession.removeAttribute("session_quizz");

        User user = userRepository.findByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("sessions", sessionRepository.getAllByUser(user.getId()));
        return "sessions";
    }

    @GetMapping("/sessions/new")
    public String newSession(Principal principal) {
        Session session = new Session(0,3);
        sessionRepository.save(session);

        User user = userRepository.findByEmail(principal.getName());
        Set<Session> sessions = user.getSessions();
        sessions.add(session);
        user.setSessions(sessions);
        userRepository.save(user);

        return "redirect:/quizz/" + session.getId();
    }

    @GetMapping("/sessions/delete/{id}")
    public String deleteSession(@PathVariable(name="id") Long id){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/genius_quizz?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC","root","of2LBdck@");
            Statement stmt = con.createStatement();
            String sql = "SET @id = '" + id + "'";
            stmt.execute(sql);
            sql = "DELETE FROM sessions_questions WHERE session_id = @id";
            stmt.execute(sql);
            con.close();
            System.out.println("Deleting sessions questions " + id + " from the Core DB");
        } catch(Exception e){ System.out.println(e);}
        sessionService.delete((id));
        return "redirect:/sessions?success";
    }
}

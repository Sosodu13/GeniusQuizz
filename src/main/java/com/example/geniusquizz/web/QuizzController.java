package com.example.geniusquizz.web;

import com.example.geniusquizz.model.Answer;
import com.example.geniusquizz.model.Question;
import com.example.geniusquizz.model.Session;
import com.example.geniusquizz.repository.AnswerRepository;
import com.example.geniusquizz.repository.QuestionRepository;
import com.example.geniusquizz.repository.UserRepository;
import com.example.geniusquizz.service.AnswerServiceImpl;
import com.example.geniusquizz.service.QuestionServiceImpl;
import com.example.geniusquizz.service.SessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;

@Controller
public class QuizzController {
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private SessionServiceImpl sessionService;
    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    private AnswerServiceImpl answerService;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/quizz/{id}")
    public String quizz(@PathVariable(name="id") Long id, Model model, Principal principal){
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));

        Session session = sessionService.findById(id);
        if(session.getLife() == 0) {
            httpSession.setAttribute("session_quizz",session);
            return "redirect:/gameover";
        }

        Question question = questionRepository.findQuestionNotInSession(id);
        model.addAttribute("question", question);
        if(question == null) {
            httpSession.setAttribute("session_quizz",session);
            return "redirect:/finish";
        }

        model.addAttribute("session_quizz_current", session);
        return "quizz";
    }

    @PostMapping("/quizz/submit")
    public String sendQuizz(@RequestParam("Radio") Long radio,
                            @RequestParam("Session") Long session_id,
                            @RequestParam("Question") Long question_id,
                            Model model){
        Answer answer = answerService.findById(radio);
        Session session = sessionService.findById(session_id);
        Question question = questionService.findById(question_id);


        if(answer.getRight_answer()){
            httpSession.setAttribute("is_correct",true);
            httpSession.setAttribute("correct_answer",answer);

            session.setScore(session.getScore() + 1);
        } else {
            httpSession.setAttribute("is_correct",false);
            Answer correct_answer = answerRepository.findGoodAnswerByQuestion(question_id);
            httpSession.setAttribute("correct_answer",correct_answer);
            session.setLife(session.getLife() - 1);
        }

        Set<Question> questions = session.getQuestions();
        questions.add(question);
        session.setQuestions(questions);
        sessionService.save(session);

        if(session.getLife() > 0){
            return "redirect:/quizz/" + session_id;
        } else {
            httpSession.setAttribute("session_quizz",session);
            return "redirect:/gameover";
        }
    }
}
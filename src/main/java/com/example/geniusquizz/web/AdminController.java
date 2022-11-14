package com.example.geniusquizz.web;

import com.example.geniusquizz.model.Answer;
import com.example.geniusquizz.model.Question;
import com.example.geniusquizz.repository.AnswerRepository;
import com.example.geniusquizz.repository.QuestionRepository;
import com.example.geniusquizz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @GetMapping("/questions")
    public String adminQuestions(Model model, Principal principal){

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
        model.addAttribute("questions", questionRepository.findAll());
        System.out.println(questionRepository.findAll());

        return "admin/questions";
    }

    @GetMapping("/question/add")
    public String adminQuestion(Model model, Principal principal){

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

        return "admin/questionAdd";
    }

    @PostMapping("question/add")
    public String adminQuestionAdd(@RequestParam("IQuest") String question,
                                   @RequestParam("IRep1") String rep1,
                                   @RequestParam("IRep2") String rep2,
                                   @RequestParam("IRep3") String rep3,
                                   @RequestParam("IRep4") String rep4,
                                   @RequestParam("Radio") String radio)
    {
        Question newQuestion = new Question();
        newQuestion.setLibelle(question);

        newQuestion = questionRepository.save(newQuestion);

        System.out.println(newQuestion);
        Answer answer1;
        Answer answer2;
        Answer answer3;
        Answer answer4;
        if (Objects.equals(radio, "rep1"))
        {
            answer1 = answerRepository.save(new Answer(rep1, true));
        }else{
            answer1 = answerRepository.save(new Answer(rep1, false));
        }
        if (Objects.equals(radio, "rep2"))
        {
            answer2 = answerRepository.save(new Answer(rep2, true));
        }else{
            answer2 = answerRepository.save(new Answer(rep2, false));
        }
        if (Objects.equals(radio, "rep3"))
        {
            answer3 = answerRepository.save(new Answer(rep3, true));
        }else{
            answer3 = answerRepository.save(new Answer(rep3, false));
        }
        if (Objects.equals(radio, "rep4"))
        {
            answer4 = answerRepository.save(new Answer(rep4, true));
        }else{
            answer4 = answerRepository.save(new Answer(rep4, false));
        }

        Collection<Answer> answerCollection = new ArrayList<>();
        answerCollection.add(answer1);
        answerCollection.add(answer2);
        answerCollection.add(answer3);
        answerCollection.add(answer4);

        newQuestion.setAnswers(answerCollection);

        newQuestion = questionRepository.save(newQuestion);

        System.out.println(newQuestion);

        return "redirect:/admin/question/add?success";
    }

}

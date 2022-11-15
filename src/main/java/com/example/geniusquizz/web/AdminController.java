package com.example.geniusquizz.web;

import com.example.geniusquizz.model.Answer;
import com.example.geniusquizz.model.Question;
import com.example.geniusquizz.repository.AnswerRepository;
import com.example.geniusquizz.repository.QuestionRepository;
import com.example.geniusquizz.repository.SessionRepository;
import com.example.geniusquizz.repository.UserRepository;
import com.example.geniusquizz.service.AnswerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    @Autowired
    AnswerServiceImpl answerService;

    @Autowired
    SessionRepository sessionRepository;

    @PersistenceContext
    public EntityManager em;

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
        model.addAttribute("users", userRepository.findAll());
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

    @GetMapping("question/edit/{id}")
    public String adminEditQuestion(@PathVariable("id") int id, Model model, Principal principal){

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
        model.addAttribute("question", questionRepository.findQuestionById((long) id));
        model.addAttribute("questions", questionRepository.findAll());

        return "admin/questionEdit";
    }

    @PostMapping("question/edit/{id}")
    public String adminPostEditQuestion(@PathVariable("id") int id,
                                        @RequestParam("IQuest") String question,
                                        @RequestParam("IRep1") String rep1,
                                        @RequestParam("IRepH1") int repH1,
                                        @RequestParam("IRep2") String rep2,
                                        @RequestParam("IRepH2") int repH2,
                                        @RequestParam("IRep3") String rep3,
                                        @RequestParam("IRepH3") int repH3,
                                        @RequestParam("IRep4") String rep4,
                                        @RequestParam("IRepH4") int repH4,
                                        @RequestParam("Radio") String radio,
                                        Model model,
                                        Principal principal){

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

        Question questionFind = questionRepository.findQuestionById((long) id);
        questionFind.setLibelle(question);

        Answer answer1 = answerService.findById((long) repH1);
        Answer answer2 = answerService.findById((long) repH2);
        Answer answer3 = answerService.findById((long) repH3);
        Answer answer4 = answerService.findById((long) repH4);
        if (Objects.equals(radio, "rep1"))
        {
            answer1.setLibelle(rep1);
            answer1.setRight_answer(true);
            answerRepository.save(answer1);
        }else{
            answer1.setLibelle(rep1);
            answer1.setRight_answer(false);
            answerRepository.save(answer1);
        }
        if (Objects.equals(radio, "rep2"))
        {
            answer2.setLibelle(rep2);
            answer2.setRight_answer(true);
            answerRepository.save(answer2);
        }else{
            answer2.setLibelle(rep2);
            answer2.setRight_answer(false);
            answerRepository.save(answer2);
        }
        if (Objects.equals(radio, "rep3"))
        {
            answer3.setLibelle(rep3);
            answer3.setRight_answer(true);
            answerRepository.save(answer3);
        }else{
            answer3.setLibelle(rep3);
            answer3.setRight_answer(false);
            answerRepository.save(answer3);
        }
        if (Objects.equals(radio, "rep4"))
        {
            answer4.setLibelle(rep4);
            answer4.setRight_answer(true);
            answerRepository.save(answer4);
        }else{
            answer4.setLibelle(rep4);
            answer4.setRight_answer(false);
            answerRepository.save(answer4);
        }

        questionRepository.save(questionFind);


        model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("questions", questionRepository.findAll());

        return "redirect:/admin/questions?successEdit";
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

    @PostMapping("/question/delete/{id}")
    public String adminQuestionDelete(@PathVariable("id") int id,
                                      Model model,
                                      Principal principal){

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

        Question questionFind = questionRepository.findQuestionById((long) id);

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/genius_quizz?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC","root","");
            Statement stmt = con.createStatement();
            String sql = "SET @id = '" + id + "'";
            stmt.execute(sql);
            sql = "DELETE FROM sessions_questions WHERE question_id = @id";
            stmt.execute(sql);
            con.close();
            System.out.println("Deleting sessions questions " + id + " from the Core DB");
        } catch(Exception e){ System.out.println(e);}

        questionRepository.delete(questionFind);


        return "redirect:/admin/questions?delete";
    }

}

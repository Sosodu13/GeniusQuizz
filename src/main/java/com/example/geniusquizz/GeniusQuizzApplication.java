package com.example.geniusquizz;

import com.example.geniusquizz.model.Answer;
import com.example.geniusquizz.model.Question;
import com.example.geniusquizz.model.Role;
import com.example.geniusquizz.model.User;
import com.example.geniusquizz.repository.QuestionRepository;
import com.example.geniusquizz.repository.RoleRepository;
import com.example.geniusquizz.repository.UserRepository;
import com.example.geniusquizz.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;

@SpringBootApplication
public class GeniusQuizzApplication {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(GeniusQuizzApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            if (roleRepository.findAll().size() == 0)
            {
                Role role = new Role("ROLE_USER");
                Role role1 = new Role("SUPER_ADMIN");
                roleRepository.save(role);
                roleRepository.save(role1);
            }
            if (questionRepository.findAll().size() == 0){

                /* QUESTION */
                Question question = new Question("Où se trouve la muraille de chine ?");
                Answer answer1 = new Answer("France",false);
                Answer answer2 = new Answer("Chine",true);
                Answer answer3 = new Answer("Espagne",false);
                Answer answer4 = new Answer("Japon",false);
                question = questionRepository.save(question);
                Collection<Answer> answers = new ArrayList<>();
                answers.add(answer1);
                answers.add(answer2);
                answers.add(answer3);
                answers.add(answer4);
                question.setAnswers(answers);
                questionRepository.save(question);
                /**/

                /* QUESTION 3 */
                Question question1 = new Question("En quelle année on est ?");
                Answer answer11 = new Answer("2022",true);
                Answer answer12 = new Answer("2020",false);
                Answer answer13 = new Answer("2018",false);
                Answer answer14 = new Answer("2000",false);
                question1 = questionRepository.save(question1);
                Collection<Answer> answers1 = new ArrayList<>();
                answers1.add(answer11);
                answers1.add(answer12);
                answers1.add(answer13);
                answers1.add(answer14);
                question1.setAnswers(answers1);
                questionRepository.save(question1);
                /**/

                /* QUESTION 3 */
                Question question2 = new Question("Combien font 1 kilomètre en mètres ?");
                Answer answer21 = new Answer("10 mètres",false);
                Answer answer22 = new Answer("1 mètre",false);
                Answer answer23 = new Answer("1000 mètres",true);
                Answer answer24 = new Answer("111 mètres",false);
                question2 = questionRepository.save(question2);
                Collection<Answer> answers2 = new ArrayList<>();
                answers2.add(answer21);
                answers2.add(answer22);
                answers2.add(answer23);
                answers2.add(answer24);
                question2.setAnswers(answers2);
                questionRepository.save(question2);
                /**/

                /* QUESTION 3 */
                Question question3 = new Question("Quelle est la couleur d'une orange ?");
                Answer answer31 = new Answer("Bleu",false);
                Answer answer32 = new Answer("Vert",false);
                Answer answer33 = new Answer("Jaune",false);
                Answer answer34 = new Answer("Orange",true);
                question3 = questionRepository.save(question3);
                Collection<Answer> answers3 = new ArrayList<>();
                answers3.add(answer31);
                answers3.add(answer32);
                answers3.add(answer33);
                answers3.add(answer34);
                question3.setAnswers(answers3);
                questionRepository.save(question3);
                /**/

                /* QUESTION 4 */
                Question question4 = new Question("Helsinki, la capitale de la Finlande, est l’une des capitales les plus froides au monde ?");
                Answer answer41 = new Answer("Oui",true);
                Answer answer42 = new Answer("Non",false);
                Answer answer43 = new Answer("Peut-être",false);
                Answer answer44 = new Answer("Peut-être pas",false);
                question4 = questionRepository.save(question4);
                Collection<Answer> answers4 = new ArrayList<>();
                answers4.add(answer41);
                answers4.add(answer42);
                answers4.add(answer43);
                answers4.add(answer44);
                question4.setAnswers(answers4);
                questionRepository.save(question4);
                /**/

                /* QUESTION 5 */
                Question question5 = new Question("Dans quel pays peut-on trouver la Catalogne, l’Andalousie et la Castille ?");
                Answer answer51 = new Answer("L'Italie",false);
                Answer answer52 = new Answer("L'Espagne",true);
                Answer answer53 = new Answer("Le Portugal",false);
                Answer answer54 = new Answer("La France",false);
                question5 = questionRepository.save(question5);
                Collection<Answer> answers5 = new ArrayList<>();
                answers5.add(answer51);
                answers5.add(answer52);
                answers5.add(answer53);
                answers5.add(answer54);
                question5.setAnswers(answers5);
                questionRepository.save(question5);
                /**/


                /* QUESTION 6 */
                Question question6 = new Question("Quel célèbre dictateur dirigea l’URSS du milieu des années 1920 à 1953 ?");
                Answer answer61 = new Answer("Molotov",false);
                Answer answer62 = new Answer("Lénine",false);
                Answer answer63 = new Answer("Staline",true);
                Answer answer64 = new Answer("Trotski",false);
                question6 = questionRepository.save(question6);
                Collection<Answer> answers6 = new ArrayList<>();
                answers6.add(answer61);
                answers6.add(answer62);
                answers6.add(answer63);
                answers6.add(answer64);
                question6.setAnswers(answers6);
                questionRepository.save(question6);
                /**/

                /* QUESTION 7 */
                Question question7 = new Question("Qui a dit : « Le sort en est jeté » (Alea jacta est) ?");
                Answer answer71 = new Answer("César",true);
                Answer answer72 = new Answer("Attila",false);
                Answer answer73 = new Answer("Auguste",false);
                Answer answer74 = new Answer("Vercingétorix",false);
                question7 = questionRepository.save(question7);
                Collection<Answer> answers7 = new ArrayList<>();
                answers7.add(answer71);
                answers7.add(answer72);
                answers7.add(answer73);
                answers7.add(answer74);
                question7.setAnswers(answers7);
                questionRepository.save(question7);
                /**/

                /* QUESTION 8 */
                Question question8 = new Question("Quel pays a remporté la coupe du monde de football en 2014 ?");
                Answer answer81 = new Answer("L'Argentine",false);
                Answer answer82 = new Answer("L'Italie",false);
                Answer answer83 = new Answer("Le Brésil",false);
                Answer answer84 = new Answer("L'Allemagne",true);
                question8 = questionRepository.save(question8);
                Collection<Answer> answers8 = new ArrayList<>();
                answers8.add(answer81);
                answers8.add(answer82);
                answers8.add(answer83);
                answers8.add(answer84);
                question8.setAnswers(answers8);
                questionRepository.save(question8);
                /**/

                /* QUESTION 9 */
                Question question9 = new Question("Dans quelle ville italienne l’action de la pièce de Shakespeare « Roméo et Juliette » se situe-t-elle ?");
                Answer answer91 = new Answer("Venise",false);
                Answer answer92 = new Answer("Vérone",true);
                Answer answer93 = new Answer("Milan",false);
                Answer answer94 = new Answer("Rome",false);
                question9 = questionRepository.save(question9);
                Collection<Answer> answers9 = new ArrayList<>();
                answers9.add(answer91);
                answers9.add(answer92);
                answers9.add(answer93);
                answers9.add(answer94);
                question9.setAnswers(answers9);
                questionRepository.save(question9);
                /**/

                /* QUESTION 10 */
                Question question10 = new Question("Par quel mot désigne-t-on une belle-mère cruelle ?");
                Answer answer101 = new Answer("Une chenapan",false);
                Answer answer102 = new Answer("Une jocrisse",false);
                Answer answer103 = new Answer("Une godiche",false);
                Answer answer104 = new Answer("Une marâtre",true);
                question10 = questionRepository.save(question10);
                Collection<Answer> answers10 = new ArrayList<>();
                answers10.add(answer101);
                answers10.add(answer102);
                answers10.add(answer103);
                answers10.add(answer104);
                question10.setAnswers(answers10);
                questionRepository.save(question10);
                /**/
            }

        };
    }
}

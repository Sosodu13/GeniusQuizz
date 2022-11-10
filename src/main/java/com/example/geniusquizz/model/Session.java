package com.example.geniusquizz.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "session")
public class Session {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "score")
    private Integer score;

    @Column(name = "life")
    private Integer life;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "sessions_questions",
            joinColumns = @JoinColumn(
                    name = "session_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "question_id", referencedColumnName = "id"
            )
    )
    private Collection<Question> questions;


    public Session(Integer score, Integer life, Collection<Question> questions) {
        super();
        this.score = score;
        this.life = life;
        this.questions = questions;
    }

    public Session() {

    }

    public Long getId() { return Id; }

    public void setId(Long id) { Id = id; }

    public Integer getScore() { return score; }

    public void setScore(Integer score) { this.score = score; }

    public Integer getLife() { return life; }

    public void setLife(Integer life) { this.life = life; }

    public Collection<Question> getQuestions() { return questions; }

    public void setQuestions(Collection<Question> questions) { this.questions = questions; }
}

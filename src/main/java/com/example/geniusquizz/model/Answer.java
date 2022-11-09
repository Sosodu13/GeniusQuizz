package com.example.geniusquizz.model;

import javax.persistence.*;

@Entity
@Table(name = "Answer")
public class Answer {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "right_answer")
    private Boolean right_answer;

    public Answer() {
    }

    public Answer(Long id, String libelle, Boolean right_answer) {
        Id = id;
        this.libelle = libelle;
        this.right_answer = right_answer;
    }

    public Long getId() { return Id; }

    public void setId(Long id) { Id = id; }

    public String getLibelle() { return libelle; }

    public void setLibelle(String libelle) { this.libelle = libelle; }

    public Boolean getRight_answer() { return right_answer; }

    public void setRight_answer(Boolean right_answer) { this.right_answer = right_answer; }
}

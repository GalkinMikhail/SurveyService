package com.example.fabriquetesttask.model;

import lombok.Getter;
import lombok.Setter;
import com.example.fabriquetesttask.model.Enums.QuestionType;

import javax.persistence.*;

@Entity
@Table(name = "questions")
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @Enumerated(EnumType.STRING)
    private QuestionType type;
}

package com.example.fabriquetesttask.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String answer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "survey_id",nullable = false)
    private Survey surveyId;

    @OneToOne
    @JoinColumn(name = "question_id",nullable = false)
    private Question questionId;
}

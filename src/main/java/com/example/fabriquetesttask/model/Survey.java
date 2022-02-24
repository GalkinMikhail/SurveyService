package com.example.fabriquetesttask.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "survey")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    @Column(name = "title")
    private String title;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @NotBlank(message = "Description cannot be empty")
    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "survey_questions",
            joinColumns = {@JoinColumn(name = "survey_id")},
            inverseJoinColumns = {@JoinColumn(name = "question_id")})
    private List<Question> questionList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id",nullable = false)
    private User creatorId;
}

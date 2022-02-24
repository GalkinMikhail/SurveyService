package com.example.fabriquetesttask.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + Id +
                ", name='" + name + '\'' +
                '}';
    }
}

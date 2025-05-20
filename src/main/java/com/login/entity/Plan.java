package com.login.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="plan")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private String validity;

    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("Plans")
    private Category category;


}

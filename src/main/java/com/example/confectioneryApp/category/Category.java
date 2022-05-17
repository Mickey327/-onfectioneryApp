package com.example.confectioneryApp.category;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "category")
@Table
@Getter
@Setter
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}

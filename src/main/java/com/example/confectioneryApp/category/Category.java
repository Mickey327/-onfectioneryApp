package com.example.confectioneryApp.category;

import lombok.*;

import javax.persistence.*;

@Entity(name = "category")
@Table
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}

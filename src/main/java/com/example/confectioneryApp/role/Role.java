package com.example.confectioneryApp.role;

import com.example.confectioneryApp.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Entity(name = "role")
@Table
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    @NotBlank(message = "Имя роли не может быть пустым")
    private String name;

    @ManyToMany(mappedBy = "roleList")
    private List<User> userList;
}

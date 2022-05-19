package com.example.confectioneryApp.user;

import com.example.confectioneryApp.cart.Cart;
import com.example.confectioneryApp.review.Review;
import com.example.confectioneryApp.role.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@Table
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Фамилия пользователя не может быть пустой")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Неверный формат почты")
    @NotBlank(message = "Почта пользователя не может быть пустой")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    @NotBlank(message = "Пароль пользователя не может быть пустой")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_to_role",
            joinColumns = @JoinColumn(
                    table = "user",
                    name = "user_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    table = "role",
                    name = "role_id",
                    referencedColumnName = "id"
            )
    )
    private List<Role> roleList;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @OneToMany(mappedBy = "user")
    private List<Review> reviewList;

    public User(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roleList = user.getRoleList();
        this.cart = user.getCart();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        roleList.stream().forEach(role -> {
            authorityList.add(new SimpleGrantedAuthority(role.getName()));
        });
        return authorityList;
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

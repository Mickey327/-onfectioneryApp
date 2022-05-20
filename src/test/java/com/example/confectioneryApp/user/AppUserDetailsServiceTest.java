package com.example.confectioneryApp.user;

import com.example.confectioneryApp.product.Product;
import com.example.confectioneryApp.review.Review;
import com.example.confectioneryApp.role.Role;
import com.example.confectioneryApp.role.RoleRepository;
import com.example.confectioneryApp.role.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class AppUserDetailsServiceTest {
    private UserRepository userRepository;
    private AppUserDetailsService userService;

    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new AppUserDetailsService(userRepository);
    }

    @Test
    void save(){
        Long id = 1L;
        User user = User.builder().id(id).firstName("firstName").build();
        userService.save(user);
        Mockito.when(userRepository.findByIdFetch(id)).thenReturn(user);
        User createdUser = userService.findByIdFetch(id);
        Assertions.assertEquals(createdUser, user);
    }

    @Test
    void findByIdFetch(){
        Long id = 1L;
        User user = User.builder().id(id).firstName("firstName").build();
        Mockito.when(userRepository.findByIdFetch(id)).thenReturn(user);
        User createdUser = userService.findByIdFetch(id);
        Assertions.assertEquals(user, createdUser);
    }
}

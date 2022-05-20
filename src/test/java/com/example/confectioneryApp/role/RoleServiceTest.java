package com.example.confectioneryApp.role;

import com.example.confectioneryApp.product.Product;
import com.example.confectioneryApp.review.Review;
import com.example.confectioneryApp.review.ReviewRepository;
import com.example.confectioneryApp.review.ReviewService;
import com.example.confectioneryApp.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class RoleServiceTest {
    private RoleRepository roleRepository;
    private RoleService roleService;

    @BeforeEach
    public void setUp() {
        roleRepository = Mockito.mock(RoleRepository.class);
        roleService = new RoleService(roleRepository);
    }

    @Test
    void findById(){
        Long id = 1L;
        Role createdRole = Role.builder()
                .id(id)
                .name("name")
                .build();
        Mockito.when(roleRepository.findById(id)).thenReturn(Optional.ofNullable(createdRole));
        Role realCreatedRole = roleService.findById(id).get();
        Assertions.assertEquals(realCreatedRole, createdRole);
    }
}

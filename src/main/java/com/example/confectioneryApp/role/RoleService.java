package com.example.confectioneryApp.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public Optional<Role> findById(Long id){
        return roleRepository.findById(id);
    }
}

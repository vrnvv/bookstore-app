package com.example.bookstoreapp.service.impl;

import com.example.bookstoreapp.model.Role;
import com.example.bookstoreapp.repository.RoleRepository;
import com.example.bookstoreapp.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findByName(String roleName) {
        return roleRepository.findByRoleName(Role.RoleName.valueOf(roleName)).orElseThrow(
                () -> new RuntimeException("Role with role name " + roleName + " not found"));
    }
}

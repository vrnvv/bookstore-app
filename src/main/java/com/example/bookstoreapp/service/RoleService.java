package com.example.bookstoreapp.service;

import com.example.bookstoreapp.model.Role;

public interface RoleService {
    Role save(Role role);

    Role findByName(String roleName);
}

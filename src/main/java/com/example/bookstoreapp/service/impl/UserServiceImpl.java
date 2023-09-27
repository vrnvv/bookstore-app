package com.example.bookstoreapp.service.impl;

import com.example.bookstoreapp.dto.userdto.UserRegistrationRequestDto;
import com.example.bookstoreapp.dto.userdto.UserResponseDto;
import com.example.bookstoreapp.model.entity.Role;
import com.example.bookstoreapp.model.entity.User;
import com.example.bookstoreapp.repository.UserRepository;
import com.example.bookstoreapp.service.RoleService;
import com.example.bookstoreapp.service.UserService;
import com.example.bookstoreapp.service.mapper.UserMapper;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("This email is already associated with an account");
        }
        User user = userMapper.toEntity(request);
        user.setRoles(Set.of(roleService.findByName(Role.RoleName.ROLE_USER.name())));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userMapper.toDto(userRepository.save(user));
    }
}

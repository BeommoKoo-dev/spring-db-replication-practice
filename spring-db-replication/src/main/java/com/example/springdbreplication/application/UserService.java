package com.example.springdbreplication.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springdbreplication.application.dto.request.UserCreateRequest;
import com.example.springdbreplication.application.dto.response.UserCreateResponse;
import com.example.springdbreplication.application.dto.response.UserGetResponse;
import com.example.springdbreplication.entity.User;
import com.example.springdbreplication.infra.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserCreateResponse createUser(UserCreateRequest request) {
        User user = request.toEntity();
        User savedUser = userRepository.save(user);

        return UserCreateResponse.from(savedUser);
    }

    @Transactional(readOnly = true)
    public UserGetResponse getUserById(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        return UserGetResponse.from(user);
    }

}

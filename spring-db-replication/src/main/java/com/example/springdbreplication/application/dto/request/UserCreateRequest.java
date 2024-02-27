package com.example.springdbreplication.application.dto.request;

import com.example.springdbreplication.entity.User;

public record UserCreateRequest(
    String name
) {
    public User toEntity() {
        return new User(name);
    }
}

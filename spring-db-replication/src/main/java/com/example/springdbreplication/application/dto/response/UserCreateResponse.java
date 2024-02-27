package com.example.springdbreplication.application.dto.response;

import com.example.springdbreplication.entity.User;

public record UserCreateResponse(
    Long userId,
    String name
) {
    public static UserCreateResponse from(User user) {
        return new UserCreateResponse(user.getUserId(), user.getName());
    }
}

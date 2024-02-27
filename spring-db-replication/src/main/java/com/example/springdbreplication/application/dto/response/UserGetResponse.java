package com.example.springdbreplication.application.dto.response;

import com.example.springdbreplication.entity.User;

public record UserGetResponse(
    Long userId,
    String name
) {

    public static UserGetResponse from(User user) {
        return new UserGetResponse(user.getUserId(), user.getName());
    }

}

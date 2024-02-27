package com.example.springdbreplication.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springdbreplication.application.UserService;
import com.example.springdbreplication.application.dto.request.UserCreateRequest;
import com.example.springdbreplication.application.dto.response.UserCreateResponse;
import com.example.springdbreplication.application.dto.response.UserGetResponse;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/users")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserCreateResponse> createUser(@RequestBody UserCreateRequest request) {
        UserCreateResponse response = userService.createUser(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserGetResponse> getUserById(@PathVariable Long userId) {
        UserGetResponse response = userService.getUserById(userId);

        return ResponseEntity.ok(response);
    }

}

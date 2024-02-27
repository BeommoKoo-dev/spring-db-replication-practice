package com.example.springdbreplication.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springdbreplication.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

package com.example.fabriquetesttask.repository;

import com.example.fabriquetesttask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}

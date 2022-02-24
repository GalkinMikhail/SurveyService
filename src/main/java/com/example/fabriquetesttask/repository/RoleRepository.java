package com.example.fabriquetesttask.repository;

import com.example.fabriquetesttask.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles,Long> {
    Roles findByName(String name);
}

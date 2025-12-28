package com.example.demo.auth.repository;

import com.example.demo.auth.entity.Role;
import com.example.demo.auth.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByRole(RoleEnum name);
}

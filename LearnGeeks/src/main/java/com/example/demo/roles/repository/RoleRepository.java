package com.example.demo.roles.repository;

import com.example.demo.roles.entity.Role;
import com.example.demo.roles.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByRole(RoleEnum name);
}

package com.example.Anarchy.domain.repository;

import com.example.Anarchy.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

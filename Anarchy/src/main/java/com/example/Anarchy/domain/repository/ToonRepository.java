package com.example.Anarchy.domain.repository;

import com.example.Anarchy.domain.model.Toon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToonRepository extends JpaRepository<Toon, Long> {
}

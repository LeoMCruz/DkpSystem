package com.example.Anarchy.domain.repository;

import com.example.Anarchy.domain.model.Run;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunRepository extends JpaRepository<Run, Long> {
}

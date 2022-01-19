package com.example.Anarchy.domain.repository;

import com.example.Anarchy.domain.model.PerfilAcesso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilAcessoRepository extends JpaRepository<PerfilAcesso, Long> {
    Optional<PerfilAcesso> findByNomePerfil(String nomePerfil);
}

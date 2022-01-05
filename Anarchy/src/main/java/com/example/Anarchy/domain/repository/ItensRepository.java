package com.example.Anarchy.domain.repository;

import com.example.Anarchy.domain.model.Itens;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItensRepository extends JpaRepository<Itens, Long> {
    @Query(value = "SELECT * FROM Itens WHERE tipo IN(\"Two-handed Sword\", " +
           "\"Dagger\", \"Shooter\", \"Fist\", \"Rapier\", \"Bow\", " +
           "\"One-handed Sword\", \"One-handed Blunt\", \"Two-handed Blunt\"," +
           "\"Ancient Sword\",\"Dual Sword\", \"One-handed Staff\", \"Two-handed Staff\", \"Spear\")",
           nativeQuery = true)
    List<Itens> findByArma(Pageable pageable);

    @Query(value = "SELECT * FROM Itens WHERE tipo IN(\"Heavy armor\", \"Light armor\", \"Robe\", \"Armor\"," +
            " \"Sigil\", \"Shield\")",
            nativeQuery = true)
    List<Itens> findByArmadura(Pageable pageable);

    @Query(value = "SELECT * FROM Itens WHERE tipo IN(\"Jewel\")",
            nativeQuery = true)
    List<Itens> findByJoia(Pageable pageable);
    @Query(value = "SELECT * FROM Itens WHERE tipo IN(\"Miscellaneous\")",
            nativeQuery = true)
    List<Itens> findByDiversos(Pageable pageable);
}

package com.example.Anarchy.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PerfilAcesso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nomePerfil;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "perfil_acesso_regras",
            joinColumns = @JoinColumn(
                    name = "perfil_acesso_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "regras_id", referencedColumnName = "id"))
    private List<Regras> regrasList;
}

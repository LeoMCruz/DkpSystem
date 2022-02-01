package com.example.Anarchy.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Toon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String nome;
    @Column
    private Long level;
    //@Transient
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn (name = "user_id")
    private User user;
    @Column
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    @Column
    private LocalDateTime dataCadastro;
    @Column
    private String role;
    @Column
    @Enumerated(EnumType.STRING)
    private CharacterTypeEnum characterTypeEnum;


}

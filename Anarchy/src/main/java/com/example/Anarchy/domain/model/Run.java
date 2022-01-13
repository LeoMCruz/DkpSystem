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
public class Run {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn (name = "raid_id")
    private Raids raids;
    @ManyToOne
    @JoinColumn (name = "item_id")
    private Itens itens;
    @Column
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    @Column
    private LocalDateTime dataCadastro;
}

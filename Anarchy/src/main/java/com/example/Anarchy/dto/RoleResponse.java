package com.example.Anarchy.dto;

import com.example.Anarchy.domain.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class RoleResponse {
    private Long id;
    private String nome;
    private String abreviacao;
    private StatusEnum status;
    private LocalDateTime dataCadastro;

}

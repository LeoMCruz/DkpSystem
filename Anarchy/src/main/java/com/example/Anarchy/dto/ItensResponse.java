package com.example.Anarchy.dto;

import com.example.Anarchy.domain.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ItensResponse {
    private Long id;
    private String nome;
    private String tipo;
    private StatusEnum status;
    private LocalDateTime dataCadastro;
    private String physical;
    private String magic;
    private String armor;
    private String imagem;
    private String quantidade;
    private String preco;

}

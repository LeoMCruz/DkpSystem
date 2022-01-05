package com.example.Anarchy.dto;

import com.example.Anarchy.domain.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Data
public class ItensRequest {
    private Long id;
    @NotNull
    @NotBlank
    private String nome;
    @NotNull
    @NotBlank
    private String tipo;
    private StatusEnum status;
    private LocalDateTime dataCadastro;
    private String physical;
    private String magic;
    private String armor;
    @NotNull
    @NotBlank
    private String imagem;
    @NotNull
    @NotBlank
    private String quantidade;
    @NotNull
    @NotBlank
    private String preco;
}

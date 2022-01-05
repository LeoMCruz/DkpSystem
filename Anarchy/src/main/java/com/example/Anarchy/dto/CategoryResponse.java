package com.example.Anarchy.dto;

import com.example.Anarchy.domain.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class CategoryResponse {
    private Long id;
    private String nome;
    private StatusEnum status;
    private LocalDateTime dataCadastro;

}

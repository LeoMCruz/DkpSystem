package com.example.Anarchy.dto;

import com.example.Anarchy.domain.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Data
public class CategoryRequest {
    private Long id;
    @NotNull
    @NotBlank
    private String nome;
    private StatusEnum status;
    private LocalDateTime dataCadastro;

}

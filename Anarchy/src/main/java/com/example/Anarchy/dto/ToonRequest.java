package com.example.Anarchy.dto;

import com.example.Anarchy.domain.model.StatusEnum;
import com.example.Anarchy.domain.model.User;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Data
public class ToonRequest {
    private Long id;
    @NotNull
    @NotBlank
    private String nome;
    private String role;
    private StatusEnum status;
    @NotNull
    @NotBlank
    private Long level;
    private LocalDateTime dataCadastro;
    private User user;

}

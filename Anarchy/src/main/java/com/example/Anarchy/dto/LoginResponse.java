package com.example.Anarchy.dto;

import com.example.Anarchy.domain.model.CharacterTypeEnum;
import com.example.Anarchy.domain.model.StatusEnum;
import com.example.Anarchy.domain.model.User;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Data
public class LoginResponse {
    private Long id;
    @NotNull
    @NotBlank
    private String nome;
    @NotNull
    @NotBlank
    private String role;
    private StatusEnum status;
    @NotNull
    private Long level;
    private LocalDateTime dataCadastro;
    private User user;
    private CharacterTypeEnum characterTypeEnum;

}

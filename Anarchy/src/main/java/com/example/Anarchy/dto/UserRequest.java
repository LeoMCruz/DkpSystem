package com.example.Anarchy.dto;

import com.example.Anarchy.domain.model.PerfilAcesso;
import com.example.Anarchy.domain.model.Regras;
import com.example.Anarchy.domain.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class UserRequest {
    private Long id;
    @NotNull
    @NotBlank
    private String senha;
    private List<Regras> regrasList;
    private PerfilAcesso perfilAcesso;
    @NotNull
    @NotBlank
    private String email;
    private StatusEnum status;
    private LocalDateTime dataCadastro;

}

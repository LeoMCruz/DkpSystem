package com.example.Anarchy.dto;

import com.example.Anarchy.domain.model.Role;
import com.example.Anarchy.domain.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ToonResponse {
    private Long id;
    private String nome;
    private Role role;
    private StatusEnum status;
    private LocalDateTime dataCadastro;

}

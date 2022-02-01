package com.example.Anarchy.dto;

import com.example.Anarchy.domain.model.CharacterTypeEnum;
import com.example.Anarchy.domain.model.StatusEnum;
import com.example.Anarchy.domain.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ToonResponse {
    private Long id;
    private String nome;
    private StatusEnum status;
    private LocalDateTime dataCadastro;
    private String role;
    private Long level;
    private User user;
    private CharacterTypeEnum characterTypeEnum;

}

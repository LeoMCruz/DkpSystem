package com.example.Anarchy.dto;

import com.example.Anarchy.domain.model.Itens;
import com.example.Anarchy.domain.model.Raids;
import com.example.Anarchy.domain.model.StatusEnum;
import com.example.Anarchy.domain.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class RunResponse {
    private Long id;
    private User user;
    private StatusEnum status;
    private LocalDateTime dataCadastro;
    private Itens itens;
    private Raids raids;

}

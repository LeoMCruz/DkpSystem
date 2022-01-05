package com.example.Anarchy.dto;

import com.example.Anarchy.domain.model.Toon;
import com.example.Anarchy.domain.model.Raids;
import com.example.Anarchy.domain.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class PontosRequest {
    private Long id;
    private Toon toon;
    private Raids raids;
    private StatusEnum status;
    private LocalDateTime dataCadastro;

}

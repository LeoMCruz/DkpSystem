package com.example.Anarchy.config.converter;

import com.example.Anarchy.domain.model.Pontos;
import com.example.Anarchy.domain.model.Raids;
import com.example.Anarchy.domain.model.Toon;
import com.example.Anarchy.dto.PontosResponse;
import org.springframework.stereotype.Component;

@Component
public class PontosResponseConverter {
    public PontosResponse fromPontos(Pontos pontos) {
        var character = pontos.getToon();
        var raids = pontos.getRaids();
        return PontosResponse.builder()
                .id(pontos.getId())
                .toon(Toon.builder()
                        .id(character.getId())
                        .level(character.getLevel())
                        .role(character.getRole())
                        .build())
                .raids(Raids.builder()
                        .id(raids.getId())
                        .nome(raids.getNome())
                        .build())
                .build();
    }
}

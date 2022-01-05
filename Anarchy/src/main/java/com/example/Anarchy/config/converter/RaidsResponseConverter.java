package com.example.Anarchy.config.converter;

import com.example.Anarchy.domain.model.Raids;
import com.example.Anarchy.dto.RaidsResponse;
import org.springframework.stereotype.Component;

@Component
public class RaidsResponseConverter {
    public RaidsResponse fromRaids(Raids raids) {
        return RaidsResponse.builder()
                .id(raids.getId())
                .nome(raids.getNome())
                .pontos(raids.getPontos())
                .build();
    }
}

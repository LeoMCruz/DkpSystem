package com.example.Anarchy.config.converter;

import com.example.Anarchy.domain.model.Toon;
import com.example.Anarchy.dto.ToonResponse;
import org.springframework.stereotype.Component;

@Component
public class ToonResponseConverter {
    public ToonResponse fromToon(Toon toon) {
        var role = toon.getRole();
        return ToonResponse.builder()
                .id(toon.getId())
                .nome(toon.getNome())
                .role(toon.getRole())
                .level(toon.getLevel())
                .build();
    }
}

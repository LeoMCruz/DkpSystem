package com.example.Anarchy.config.converter;

import com.example.Anarchy.domain.model.Role;
import com.example.Anarchy.dto.RoleResponse;
import org.springframework.stereotype.Component;

@Component
public class RoleResponseConverter {
    public RoleResponse fromRole(Role role) {
        return RoleResponse.builder()
                .id(role.getId())
                .abreviacao(role.getAbreviacao())
                .nome(role.getNome())
                .build();
    }
}

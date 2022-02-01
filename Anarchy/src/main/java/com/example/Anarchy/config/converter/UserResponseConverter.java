package com.example.Anarchy.config.converter;

import com.example.Anarchy.domain.model.PerfilAcesso;
import com.example.Anarchy.domain.model.User;
import com.example.Anarchy.dto.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserResponseConverter {
    public UserResponse fromUser(User user) {
        var perfilAcesso = user.getPerfilAcesso();
        return UserResponse.builder()
                .senha(user.getSenha())
                .email(user.getEmail())
                .perfilAcesso(PerfilAcesso.builder()
                        .id(perfilAcesso.getId())
                        .build())
                .build();
    }
}

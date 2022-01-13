package com.example.Anarchy.config.converter;

import com.example.Anarchy.domain.model.User;
import com.example.Anarchy.dto.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserResponseConverter {
    public UserResponse fromUser(User user) {
        return UserResponse.builder()
                .login(user.getLogin())
                .senha(user.getSenha())
                .email(user.getEmail())
                .build();
    }
}

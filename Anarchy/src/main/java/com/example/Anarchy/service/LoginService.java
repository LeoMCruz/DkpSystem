package com.example.Anarchy.service;

import com.example.Anarchy.config.converter.LoginResponseConverter;
import com.example.Anarchy.domain.model.StatusEnum;
import com.example.Anarchy.domain.model.User;
import com.example.Anarchy.domain.repository.UserRepository;
import com.example.Anarchy.dto.LoginRequest;
import com.example.Anarchy.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final LoginResponseConverter loginResponseConverter;

    public UserResponse salvarUser(LoginRequest loginRequest){
        var regras = loginRequest.getRegrasList();
        var user = User.builder()
                .login(loginRequest.getLogin())
                .email(loginRequest.getEmail())
                .senha(passwordEncoder.encode(loginRequest.getSenha()))
                .dataCadastro(LocalDateTime.now())
                .status(StatusEnum.ATIVO)
                .build();
        userRepository.save(user);
        return loginResponseConverter.fromUser(user);
    }
    public List<UserResponse> buscarTodos(Pageable pageable){
        return userRepository.findAll(pageable).stream()
                .map(login -> loginResponseConverter.fromUser(login)).collect(Collectors.toList());
    }





    //buscar user está em ImplementsUserDetaisService
}

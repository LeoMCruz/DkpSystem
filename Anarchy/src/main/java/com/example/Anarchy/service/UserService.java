package com.example.Anarchy.service;

import com.example.Anarchy.config.converter.UserResponseConverter;
import com.example.Anarchy.domain.model.StatusEnum;
import com.example.Anarchy.domain.model.User;
import com.example.Anarchy.domain.repository.UserRepository;
import com.example.Anarchy.dto.UserRequest;
import com.example.Anarchy.dto.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserResponseConverter userResponseConverter;

    public UserService(UserRepository userRepository, UserResponseConverter userResponseConverter, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userResponseConverter = userResponseConverter;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse salvarUser(UserRequest userRequest){
        var regras = userRequest.getRegrasList();
        var user = User.builder()
                .login(userRequest.getLogin())
                .email(userRequest.getEmail())
                .senha(passwordEncoder.encode(userRequest.getSenha())) //LEO FDP
                .dataCadastro(LocalDateTime.now())
                .status(StatusEnum.ATIVO)
                .regrasList(userRequest.getRegrasList())
                .build();
        userRepository.save(user);
        return userResponseConverter.fromUser(user);
    }





    //buscar user está em ImplementsUserDetaisService
}

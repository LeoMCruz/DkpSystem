package com.example.Anarchy.service;

import com.example.Anarchy.config.converter.LoginResponseConverter;
import com.example.Anarchy.domain.model.*;
import com.example.Anarchy.domain.repository.ToonRepository;
import com.example.Anarchy.domain.repository.UserRepository;
import com.example.Anarchy.dto.LoginRequest;
import com.example.Anarchy.dto.ToonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final LoginResponseConverter loginResponseConverter;
    private final ToonRepository toonRepository;

//    public UserResponse salvarUser(LoginRequest loginRequest){
//        var regras = loginRequest.getRegrasList();
//        var perfilAcesso = loginRequest.getPerfilAcesso();
//        var toon = loginRequest.getToon();
//        var user = User.builder()
//                .email(loginRequest.getEmail())
//                .senha(passwordEncoder.encode(loginRequest.getSenha()))
//                .dataCadastro(LocalDateTime.now())
//                .status(StatusEnum.ATIVO)
//                .clan(loginRequest.getClan())
//                .perfilAcesso(PerfilAcesso.builder()
//                        .id(perfilAcesso.getId())
//                        .build())
//                .toon(Toon.builder()
//                        .id(toon.getId())
//                        .nome(toon.getNome())
//                        .level(toon.getLevel())
//                        .role(toon.getRole())
//                        .build())
//                .build();
//        userRepository.save(user);
//        return loginResponseConverter.fromUser(user);
//
//    }

    public ToonResponse salvarRegistro(@Valid LoginRequest loginRequest){
        var user = loginRequest.getUser();
        var perfilAcesso = loginRequest.getUser().getPerfilAcesso();
        var toon = Toon.builder()
                .id(loginRequest.getId())
                .nome(loginRequest.getNome())
                .status(StatusEnum.ATIVO)
                .dataCadastro(LocalDateTime.now())
                .role(loginRequest.getRole())
                .level(loginRequest.getLevel())
                .characterTypeEnum(CharacterTypeEnum.MAIN)
                .user(User.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .senha(passwordEncoder.encode(user.getSenha()))
                        .dataCadastro(LocalDateTime.now())
                        .perfilAcesso(PerfilAcesso.builder()
                            .id(perfilAcesso.getId())
                            .build())
                        .status(StatusEnum.ATIVO)
                        .dataCadastro(LocalDateTime.now())
                        .build())
                .build();
        toonRepository.save(toon);
        return loginResponseConverter.fromToon(toon);
    }
    public boolean verificaRegistro(@Valid LoginRequest toonRequest){
        return toonRepository.existsByNome(toonRequest.getNome()) ||
                userRepository.existsByEmail(toonRequest.getUser().getEmail());
    }



    //buscar user está em ImplementsUserDetaisService
}

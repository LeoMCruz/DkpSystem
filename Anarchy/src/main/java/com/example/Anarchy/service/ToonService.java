package com.example.Anarchy.service;

import com.example.Anarchy.config.converter.ToonResponseConverter;
import com.example.Anarchy.domain.model.CharacterTypeEnum;
import com.example.Anarchy.domain.model.StatusEnum;
import com.example.Anarchy.domain.model.Toon;
import com.example.Anarchy.domain.model.User;
import com.example.Anarchy.domain.repository.ToonRepository;
import com.example.Anarchy.domain.repository.UserRepository;
import com.example.Anarchy.dto.ToonRequest;
import com.example.Anarchy.dto.ToonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToonService {
    private final ToonRepository toonRepository;
    private final UserRepository userRepository;
    private final ToonResponseConverter toonResponseConverter;
    private final PasswordEncoder passwordEncoder;

    public ToonResponse salvarToon(ToonRequest toonRequest){
        var user = toonRequest.getUser();
        var toon = Toon.builder()
                .id(toonRequest.getId())
                .nome(toonRequest.getNome())
                .status(StatusEnum.ATIVO)
                .dataCadastro(LocalDateTime.now())
                .role(toonRequest.getRole())
                .level(toonRequest.getLevel())
                .user(User.builder()
                        .id(user.getId())
                        .build())
                .build();
        toonRepository.save(toon);
        return toonResponseConverter.fromToon(toon);
    }
    public List<ToonResponse> buscarTodos(Pageable pageable){
        return toonRepository.findAll(pageable).stream()
                .map(toon -> toonResponseConverter.fromToon(toon)).collect(Collectors.toList());
    }
    public ToonResponse buscarById(Long id){
        return toonRepository.findById(id).map(toon -> toonResponseConverter.fromToon(toon)).orElse(null);
    }

    public ToonResponse salvarRegistro(ToonRequest toonRequest){
        var user = toonRequest.getUser();
        var toon = Toon.builder()
                .id(toonRequest.getId())
                .nome(toonRequest.getNome())
                .status(StatusEnum.ATIVO)
                .dataCadastro(LocalDateTime.now())
                .role(toonRequest.getRole())
                .level(toonRequest.getLevel())
                .characterTypeEnum(CharacterTypeEnum.ALT)
                .user(User.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .senha(passwordEncoder.encode(user.getSenha()))
                        .status(StatusEnum.ATIVO)
                        .dataCadastro(LocalDateTime.now())
                        .build())
                .build();
        toonRepository.save(toon);
        return toonResponseConverter.fromToon(toon);
    }
    public boolean verificaRegistro(ToonRequest toonRequest){
       return toonRepository.existsByNome(toonRequest.getNome()) ||
               userRepository.existsByEmail(toonRequest.getUser().getEmail());
    }
}

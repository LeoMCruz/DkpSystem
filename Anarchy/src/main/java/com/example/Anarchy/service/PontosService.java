package com.example.Anarchy.service;

import com.example.Anarchy.config.converter.PontosResponseConverter;
import com.example.Anarchy.domain.model.*;
import com.example.Anarchy.domain.model.Toon;
import com.example.Anarchy.domain.repository.PontosRepository;
import com.example.Anarchy.dto.PontosRequest;
import com.example.Anarchy.dto.PontosResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PontosService {
    private final PontosRepository pontosRepository;
    private final PontosResponseConverter pontosResponseConverter;

    public PontosService(PontosRepository pontosRepository, PontosResponseConverter pontosResponseConverter) {
        this.pontosRepository = pontosRepository;
        this.pontosResponseConverter = pontosResponseConverter;
    }

    public PontosResponse salvarPontos(PontosRequest pontosRequest){
        var character = pontosRequest.getToon();
        var raids = pontosRequest.getRaids();
        var role = character.getRole();
        var pontos = Pontos.builder()
                .id(pontosRequest.getId())
                .toon(Toon.builder()
                        .id(character.getId())
                        .level(character.getLevel())
                        .role(Role.builder()
                                .id(role.getId())
                                .abreviacao(role.getAbreviacao())
                                .build())
                        .build())
                .raids(Raids.builder()
                        .id(raids.getId())
                        .nome(raids.getNome())
                        .pontos(raids.getPontos())
                        .build())
                .dataCadastro(LocalDateTime.now())
                .status(StatusEnum.ATIVO)
                .build();
        pontosRepository.save(pontos);
        return pontosResponseConverter.fromPontos(pontos);
    }
    public List<PontosResponse> buscarTodos(Pageable pageable){
        return pontosRepository.findAll(pageable).stream()
                .map(pontos -> pontosResponseConverter.fromPontos(pontos)).collect(Collectors.toList());
    }
    public PontosResponse buscarById(Long id){
        return pontosRepository.findById(id).map(pontos -> pontosResponseConverter.fromPontos(pontos)).orElse(null);
    }
}

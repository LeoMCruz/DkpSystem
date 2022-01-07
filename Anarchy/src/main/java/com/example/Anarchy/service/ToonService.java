package com.example.Anarchy.service;

import com.example.Anarchy.config.converter.ToonResponseConverter;
import com.example.Anarchy.domain.model.StatusEnum;
import com.example.Anarchy.domain.model.Toon;
import com.example.Anarchy.domain.repository.ToonRepository;
import com.example.Anarchy.dto.ToonRequest;
import com.example.Anarchy.dto.ToonResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToonService {
    private final ToonRepository toonRepository;
    private final ToonResponseConverter toonResponseConverter;

    public ToonService(ToonRepository toonRepository, ToonResponseConverter toonResponseConverter) {
        this.toonRepository = toonRepository;
        this.toonResponseConverter = toonResponseConverter;
    }

    public ToonResponse salvarToon(ToonRequest toonRequest){
        var classeResquest = toonRequest.getRole();

        var toon = Toon.builder()
                .id(toonRequest.getId())
                .nome(toonRequest.getNome())
                .status(StatusEnum.ATIVO)
                .dataCadastro(LocalDateTime.now())
                .role(toonRequest.getRole())
                .level(toonRequest.getLevel())
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
}

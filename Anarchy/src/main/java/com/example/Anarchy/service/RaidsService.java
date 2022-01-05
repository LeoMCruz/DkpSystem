package com.example.Anarchy.service;

import com.example.Anarchy.config.converter.RaidsResponseConverter;
import com.example.Anarchy.domain.model.Raids;
import com.example.Anarchy.domain.model.StatusEnum;
import com.example.Anarchy.domain.repository.RaidsRepository;
import com.example.Anarchy.dto.RaidsRequest;
import com.example.Anarchy.dto.RaidsResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaidsService {
    private final RaidsRepository raidsRepository;
    private final RaidsResponseConverter raidsResponseConverter;

    public RaidsService(RaidsRepository raidsRepository, RaidsResponseConverter raidsResponseConverter) {
        this.raidsRepository = raidsRepository;
        this.raidsResponseConverter = raidsResponseConverter;
    }


    public RaidsResponse salvarRaids(RaidsRequest raidsRequest){
         var raids = Raids.builder()
                 .id(raidsRequest.getId())
                 .nome(raidsRequest.getNome())
                 .pontos(raidsRequest.getPontos())
                 .status(StatusEnum.ATIVO)
                 .dataCadastro(LocalDateTime.now())
                 .build();
        raidsRepository.save(raids);
        return raidsResponseConverter.fromRaids(raids);
    }
    public List<RaidsResponse> buscarTodos(Pageable pageable){
        return raidsRepository.findAll(pageable).stream()
                .map(raids -> raidsResponseConverter.fromRaids(raids)).collect(Collectors.toList());
    }
    public RaidsResponse buscarById(Long id){
        return raidsRepository.findById(id).map(raids -> raidsResponseConverter.fromRaids(raids)).orElse(null);
    }
}

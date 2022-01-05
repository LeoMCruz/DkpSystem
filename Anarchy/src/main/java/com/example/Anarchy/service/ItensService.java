package com.example.Anarchy.service;

import com.example.Anarchy.config.converter.ItensResponseConverter;
import com.example.Anarchy.domain.model.Itens;
import com.example.Anarchy.domain.model.StatusEnum;
import com.example.Anarchy.domain.repository.ItensRepository;
import com.example.Anarchy.dto.ItensRequest;
import com.example.Anarchy.dto.ItensResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItensService {
    private final ItensRepository itensRepository;
    private final ItensResponseConverter itensResponseConverter;

    public ItensService(ItensRepository itensRepository, ItensResponseConverter itensResponseConverter) {
        this.itensRepository = itensRepository;
        this.itensResponseConverter = itensResponseConverter;
    }

    public ItensResponse salvarItens(ItensRequest itensRequest){
        var itens = Itens.builder()
                 .id(itensRequest.getId())
                 .nome(itensRequest.getNome())
                 .status(StatusEnum.ATIVO)
                 .dataCadastro(LocalDateTime.now())
                 .tipo(itensRequest.getTipo())
                 .magic(itensRequest.getMagic())
                 .physical(itensRequest.getPhysical())
                 .armor(itensRequest.getArmor())
                 .quantidade(itensRequest.getQuantidade())
                 .imagem(itensRequest.getImagem())
                 .preco(itensRequest.getPreco())
                 .build();
        itensRepository.save(itens);
        return itensResponseConverter.fromItens(itens);
    }
    public List<ItensResponse> buscarTodos(Pageable pageable){
        return itensRepository.findAll(pageable).stream()
                .map(itens -> itensResponseConverter.fromItens(itens)).collect(Collectors.toList());
    }
    public ItensResponse buscarById(Long id){
        return itensRepository.findById(id).map(itens -> itensResponseConverter.fromItens(itens)).orElse(null);
    }
    public List<ItensResponse> buscarArmas(Pageable pageable){
        return itensRepository.findByArma(pageable).stream()
                .map(itens -> itensResponseConverter.fromItens(itens)).collect(Collectors.toList());
    }
    public List<ItensResponse> buscarArmadura(Pageable pageable){
        return itensRepository.findByArmadura(pageable).stream()
                .map(itens -> itensResponseConverter.fromItens(itens)).collect(Collectors.toList());
    }
    public List<ItensResponse> buscarJoia(Pageable pageable){
        return itensRepository.findByJoia(pageable).stream()
                .map(itens -> itensResponseConverter.fromItens(itens)).collect(Collectors.toList());
    }
    public List<ItensResponse> buscarDiversos(Pageable pageable){
        return itensRepository.findByDiversos(pageable).stream()
                .map(itens -> itensResponseConverter.fromItens(itens)).collect(Collectors.toList());
    }
}

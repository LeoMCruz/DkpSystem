package com.example.Anarchy.config.converter;

import com.example.Anarchy.domain.model.Itens;
import com.example.Anarchy.dto.ItensResponse;
import org.springframework.stereotype.Component;

@Component
public class ItensResponseConverter {
    public ItensResponse fromItens(Itens itens) {
        return ItensResponse.builder()
                .id(itens.getId())
                .nome(itens.getNome())
                .tipo(itens.getTipo())
                .armor(itens.getArmor())
                .imagem(itens.getImagem())
                .magic(itens.getMagic())
                .physical(itens.getPhysical())
                .quantidade(itens.getQuantidade())
                .preco(itens.getPreco())
                .build();
    }
}

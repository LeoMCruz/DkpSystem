package com.example.Anarchy.service;

import com.example.Anarchy.config.converter.RoleResponseConverter;
import com.example.Anarchy.domain.model.Role;
import com.example.Anarchy.domain.model.StatusEnum;
import com.example.Anarchy.domain.repository.RoleRepository;
import com.example.Anarchy.dto.RoleRequest;
import com.example.Anarchy.dto.RoleResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleResponseConverter roleResponseConverter;

    public RoleService(RoleRepository roleRepository, RoleResponseConverter roleResponseConverter) {
        this.roleRepository = roleRepository;
        this.roleResponseConverter = roleResponseConverter;
    }


    public RoleResponse salvarRole(RoleRequest roleRequest){
         var role = Role.builder()
                 .id(roleRequest.getId())
                 .abreviacao(roleRequest.getAbreviacao())
                 .nome(roleRequest.getNome())
                 .status(StatusEnum.ATIVO)
                 .dataCadastro(LocalDateTime.now())
                 .build();
        roleRepository.save(role);
        return roleResponseConverter.fromRole(role);
    }
    public List<RoleResponse> buscarTodos(Pageable pageable){
        return roleRepository.findAll(pageable).stream()
                .map(role -> roleResponseConverter.fromRole(role)).collect(Collectors.toList());
    }
    public RoleResponse buscarById(Long id){
        return roleRepository.findById(id).map(role -> roleResponseConverter.fromRole(role)).orElse(null);
    }
}

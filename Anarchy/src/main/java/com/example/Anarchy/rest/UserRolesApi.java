package com.example.Anarchy.rest;

import com.example.Anarchy.domain.repository.PerfilAcessoRepository;
import com.example.Anarchy.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4000", maxAge = 3600)
@RestController
public class UserRolesApi {
    private final UserRepository userRepository;
    private final PerfilAcessoRepository perfilAcessoRepository;

    @PutMapping(value = "/api/v1/user/{userId}/role/{permissao}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addRole(@PathVariable Long userId, @PathVariable String permissao){
        var user = userRepository.findById(userId).orElseThrow();
        var perfilAcesso = perfilAcessoRepository.findByNomePerfil(permissao).orElseThrow();
        user.setPerfilAcesso(perfilAcesso);
        userRepository.save(user);
        return null;
    }

}

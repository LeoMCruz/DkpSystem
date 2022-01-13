package com.example.Anarchy.rest;

import com.example.Anarchy.domain.model.PermissaoEnum;
import com.example.Anarchy.domain.repository.RegrasRepository;
import com.example.Anarchy.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4000", maxAge = 3600)
@RestController
public class UserRolesApi {
    private final UserRepository userRepository;
    private final RegrasRepository regrasRepository;

    @PutMapping(value = "/api/v1/user/{userId}/role/{permissao}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addRole(@PathVariable Long userId, @PathVariable PermissaoEnum permissao){
        var user = userRepository.findById(userId).orElseThrow();
        var rolesId = regrasRepository.findAllById(Arrays.asList(permissao.getRoleID()));
        user.setRegrasList(rolesId);
        userRepository.save(user);
        return null;
    }
}

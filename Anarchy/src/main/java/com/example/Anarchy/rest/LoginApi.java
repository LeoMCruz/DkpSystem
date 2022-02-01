package com.example.Anarchy.rest;

import com.example.Anarchy.dto.LoginRequest;
import com.example.Anarchy.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4000", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class LoginApi {
   private final LoginService loginService;


    @PostMapping(value = "/api/v1/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@Valid @RequestBody LoginRequest loginRequest){
        if(loginService.verificaRegistro(loginRequest))
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(loginService.salvarRegistro(loginRequest));
    }
}

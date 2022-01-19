package com.example.Anarchy.rest;

import com.example.Anarchy.domain.repository.UserRepository;
import com.example.Anarchy.dto.LoginRequest;
import com.example.Anarchy.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4000", maxAge = 3600)
@RestController
public class LoginApi {
    private final LoginService loginService;

    public LoginApi(LoginService loginService, UserRepository userRepository) {
        this.loginService = loginService;
    }

    @PostMapping(value = "/api/v1/register/new", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody LoginRequest loginRequest){
        if(loginRequest.getLogin() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(loginService.salvarUser(loginRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(loginService.salvarUser(loginRequest));
    }

}

package com.example.Anarchy.rest;

import com.example.Anarchy.dto.PontosRequest;
import com.example.Anarchy.dto.PontosResponse;
import com.example.Anarchy.service.PontosService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4000", maxAge = 3600)
@RestController
public class PontosApi {
    private final PontosService pontosService;

    public PontosApi(PontosService pontosService) {
        this.pontosService = pontosService;
    }

    @PostMapping(value = "/api/v1/pontos", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody PontosRequest pontosRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(pontosRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(pontosService.salvarPontos(pontosRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pontosService.salvarPontos(pontosRequest));

    }
    @GetMapping(value = "/api/v1/pontos", produces = "application/json")
    public ResponseEntity<List<PontosResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(pontosService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/pontos/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var pontosResponse= pontosService.buscarById(id);
        if(pontosResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(pontosResponse);
    }
}

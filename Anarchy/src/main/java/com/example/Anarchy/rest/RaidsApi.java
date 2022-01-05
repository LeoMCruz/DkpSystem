package com.example.Anarchy.rest;

import com.example.Anarchy.dto.RaidsRequest;
import com.example.Anarchy.dto.RaidsResponse;
import com.example.Anarchy.service.RaidsService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4000", maxAge = 3600)
@RestController
    public class RaidsApi {
     private final RaidsService raidsService;

    public RaidsApi(RaidsService raidsService) {
        this.raidsService = raidsService;
    }

    @PostMapping(value = "/api/v1/raids", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody RaidsRequest raidsRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(raidsRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(raidsService.salvarRaids(raidsRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(raidsService.salvarRaids(raidsRequest));

    }
    @GetMapping(value = "/api/v1/raids", produces = "application/json")
    public ResponseEntity<List<RaidsResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(raidsService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/raids/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var raidsResponse = raidsService.buscarById(id);
        if(raidsResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(raidsResponse);
    }
}

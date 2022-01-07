package com.example.Anarchy.rest;

import com.example.Anarchy.domain.model.Toon;
import com.example.Anarchy.domain.repository.ToonRepository;
import com.example.Anarchy.dto.ToonRequest;
import com.example.Anarchy.dto.ToonResponse;
import com.example.Anarchy.service.ToonService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4000", maxAge = 3600)
@RestController
public class ToonApi {
    private final ToonService toonService;
    private final ToonRepository toonRepository;

    public ToonApi(ToonService toonService, ToonRepository toonRepository) {
        this.toonService = toonService;
        this.toonRepository = toonRepository;
    }
    @PostMapping(value = "/api/v1/toon", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody ToonRequest toonRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(toonRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(toonService.salvarToon(toonRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(toonService.salvarToon(toonRequest));

    }
    @GetMapping(value = "/api/v1/toon", produces = "application/json")
    public ResponseEntity<List<ToonResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(toonService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/toon/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var toonResponse = toonService.buscarById(id);
        if(toonResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(toonResponse);
    }
    @PutMapping(value="api/v1/toon/{id}")
    public ResponseEntity<Toon> update(@PathVariable Long id, @RequestBody Toon changeLvl){
        return toonRepository.findById(id)
                .map(toon -> {
                    toon.setLevel(changeLvl.getLevel());
                    Toon lvlUpdated = toonRepository.save(toon);
                    return ResponseEntity.ok().body(lvlUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
}

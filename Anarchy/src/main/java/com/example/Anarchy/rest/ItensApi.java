package com.example.Anarchy.rest;

import com.example.Anarchy.domain.model.Itens;
import com.example.Anarchy.domain.repository.ItensRepository;
import com.example.Anarchy.dto.ItensRequest;
import com.example.Anarchy.dto.ItensResponse;
import com.example.Anarchy.service.ItensService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4000", maxAge = 3600)
@RestController
public class ItensApi {
    private final ItensService itensService;
    private final ItensRepository itensRepository;

    public ItensApi(ItensService itensService, ItensRepository itensRepository1) {
        this.itensService = itensService;
        this.itensRepository = itensRepository1;
    }

    @PostMapping(value = "/api/v1/itens", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody ItensRequest itensRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(itensRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(itensService.salvarItens(itensRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(itensService.salvarItens(itensRequest));
    }
    @PutMapping(value="api/v1/itens/{id}")
    public ResponseEntity<Itens> update(@PathVariable Long id, @RequestBody Itens newItens){
        return itensRepository.findById(id)
                .map(itens -> {
            itens.setQuantidade(newItens.getQuantidade());
            itens.setPreco(newItens.getPreco());
            Itens itensUpdated = itensRepository.save(itens);
            return ResponseEntity.ok().body(itensUpdated);
        }).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping(value = "/api/v1/itens", produces = "application/json")
    public ResponseEntity<List<ItensResponse>> findAll(@PageableDefault(page = 0, size = 9999, sort = { "nome", "id" }, direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(itensService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/itens/armas", produces = "application/json")
    public ResponseEntity<List<ItensResponse>> findByArma(@PageableDefault(page = 0, size = 9999, sort = { "nome", "id" }, direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(itensService.buscarArmas(pageable));
    }
    @GetMapping(value = "/api/v1/itens/armaduras", produces = "application/json")
    public ResponseEntity<List<ItensResponse>> findByArmadura(@PageableDefault(page = 0, size = 9999, sort = { "nome", "id" }, direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(itensService.buscarArmadura(pageable));
    }
    @GetMapping(value = "/api/v1/itens/joias", produces = "application/json")
    public ResponseEntity<List<ItensResponse>> findByJoia(@PageableDefault(page = 0, size = 9999, sort = { "nome", "id" }, direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(itensService.buscarJoia(pageable));
    }
    @GetMapping(value = "/api/v1/itens/diversos", produces = "application/json")
    public ResponseEntity<List<ItensResponse>> findByDiveros(@PageableDefault(page = 0, size = 9999, sort = { "nome", "id" }, direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(itensService.buscarDiversos(pageable));
    }

    @GetMapping(value = "/api/v1/itens/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var itensResponse = itensService.buscarById(id);
        if(itensResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(itensResponse);
    }
}
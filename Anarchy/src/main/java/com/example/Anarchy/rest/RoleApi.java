package com.example.Anarchy.rest;

import com.example.Anarchy.dto.RoleRequest;
import com.example.Anarchy.dto.RoleResponse;
import com.example.Anarchy.service.RoleService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4000", maxAge = 3600)
@RestController
public class RoleApi {
    private final RoleService roleService;

    public RoleApi(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(value = "/api/v1/role", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody @Valid RoleRequest roleRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(roleRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(roleService.salvarRole(roleRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roleService.salvarRole(roleRequest));

    }
    @GetMapping(value = "/api/v1/role", produces = "application/json")
    public ResponseEntity<List<RoleResponse>> findAll(@PageableDefault(page = 0, size = 9999, sort = { "nome", "id" }, direction = Sort.Direction.ASC)  Pageable pageable){
        return ResponseEntity.ok(roleService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/role/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var roleResponse = roleService.buscarById(id);
        if(roleResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(roleResponse);
    }
}

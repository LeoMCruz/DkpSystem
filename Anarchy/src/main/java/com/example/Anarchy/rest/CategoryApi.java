package com.example.Anarchy.rest;

import com.example.Anarchy.dto.CategoryRequest;
import com.example.Anarchy.dto.CategoryResponse;
import com.example.Anarchy.service.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4000", maxAge = 3600)
@RestController
public class CategoryApi {
    private final CategoryService categoryService;

    public CategoryApi(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(value = "/api/v1/category", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody CategoryRequest categoryRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(categoryRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(categoryService.salvarCategory(categoryRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.salvarCategory(categoryRequest));

    }
    @GetMapping(value = "/api/v1/category", produces = "application/json")
    public ResponseEntity<List<CategoryResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(categoryService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/category/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var categoryResponse = categoryService.buscarById(id);
        if(categoryResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(categoryResponse);
    }
}

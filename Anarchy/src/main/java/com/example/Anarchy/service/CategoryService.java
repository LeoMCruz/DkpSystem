package com.example.Anarchy.service;

import com.example.Anarchy.config.converter.CategoryResponseConverter;
import com.example.Anarchy.domain.model.Category;
import com.example.Anarchy.domain.model.StatusEnum;
import com.example.Anarchy.domain.repository.CategoryRepository;
import com.example.Anarchy.dto.CategoryRequest;
import com.example.Anarchy.dto.CategoryResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryResponseConverter categoryResponseConverter;

    public CategoryService(CategoryRepository categoryRepository, CategoryResponseConverter categoryResponseConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryResponseConverter = categoryResponseConverter;
    }


    public CategoryResponse salvarCategory(CategoryRequest categoryRequest){
         var category = Category.builder()
                 .id(categoryRequest.getId())
                 .nome(categoryRequest.getNome())
                 .dataCadastro(LocalDateTime.now())
                 .status(StatusEnum.ATIVO)
                 .build();
        categoryRepository.save(category);
        return categoryResponseConverter.fromCategory(category);
    }
    public List<CategoryResponse> buscarTodos(Pageable pageable){
        return categoryRepository.findAll(pageable).stream()
                .map(category -> categoryResponseConverter.fromCategory(category)).collect(Collectors.toList());
    }
    public CategoryResponse buscarById(Long id){
        return categoryRepository.findById(id).map(category -> categoryResponseConverter.fromCategory(category)).orElse(null);
    }
}

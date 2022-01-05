package com.example.Anarchy.config.converter;

import com.example.Anarchy.domain.model.Category;
import com.example.Anarchy.dto.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryResponseConverter {
    public CategoryResponse fromCategory(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .nome(category.getNome())
                .build();
    }
}

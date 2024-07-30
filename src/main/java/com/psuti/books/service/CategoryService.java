package com.psuti.books.service;

import com.psuti.books.dto.CategoryDTO;
import com.psuti.books.model.Category;
import com.psuti.books.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public Category create(CategoryDTO dto) {
        return categoryRepository.save(Category.builder()
                .name(dto.getName())
                .idParent(categoryRepository.findById(dto.getIdParent()).orElse(null))
                .multiSelect(dto.isMultiSelect())
                .build());
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public List<CategoryDTO> getAll() {
        List<CategoryDTO> dtos = new ArrayList<>();
        for (Category category : categoryRepository.findAll()) {
            dtos.add(CategoryDTO.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .idParent(category.getIdParent() == null ? 0 : category.getIdParent().getId())
                    .multiSelect(category.isMultiSelect())
                    .build());
        }
        return dtos;
    }

    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}


package com.psuti.books.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDTO {
    private Long id;
    private String name;
    private Long idParent;
    private boolean multiSelect;
}

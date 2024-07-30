package com.psuti.books.dto;

import lombok.Data;

@Data
public class BookResponseDTO {
    private Long id;
    private Long bookLiteraryId;
    private Long userId;
    private String response;
    private String note;
}

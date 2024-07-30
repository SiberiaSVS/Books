package com.psuti.books.dto;

import lombok.Data;

@Data
public class BookLiteraryDTO {
    private Long id;
    private Long autorId;
    private String bookName;
    private String note;
}

package com.psuti.books.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OfferListDTO {
    private Long id;
    private Long bookLiteraryId;
    private String isbn;
    private Date yearPublishing;
    private List<Long> categories;
}

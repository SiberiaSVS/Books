package com.psuti.books.dto;

import lombok.Data;

@Data
public class UserExchangeListDTO {
    private Long id;
    private Long exchangeListId;
    private Long offerListId;
    private String trackNumber;
    private boolean receiving;
}

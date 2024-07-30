package com.psuti.books.dto;

import lombok.Data;

import java.util.List;

@Data
public class WishListDTO {
    private Long id;
    private Long userAddressId;
    private List<Long> categories;
    private Long offerListId;
}

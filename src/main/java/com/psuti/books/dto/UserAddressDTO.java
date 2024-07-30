package com.psuti.books.dto;

import lombok.Data;

@Data
public class UserAddressDTO {
    private Long id;
    private String addrIndex;
    private String addrCity;
    private String addrStreet;
    private String addrHouse;
    private String addrStructure;
    private String AddrApart;
    private boolean isDefault;
}

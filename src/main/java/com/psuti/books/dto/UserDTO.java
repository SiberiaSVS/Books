package com.psuti.books.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String secondName;
    private String email;
    private String userName;
    private String password;
    private byte[] avatar;
}

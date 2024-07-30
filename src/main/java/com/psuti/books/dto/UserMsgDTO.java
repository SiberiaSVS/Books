package com.psuti.books.dto;

import lombok.Data;

@Data
public class UserMsgDTO {
    private Long id;
    private Long userId;
    private String text;
    private String notes;
    private Long statusId;
    private int type;
}

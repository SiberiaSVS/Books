package com.psuti.books.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idBookLiterary", nullable = false)
    private BookLiterary bookLiterary;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @Column(nullable = false)
    private Date createAt;

    @Column(nullable = false, length = 500)
    private String response;

    @Column(length = 50)
    private String note;
}

package com.psuti.books.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfferList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IdBookLiterary", nullable = false)
    private BookLiterary bookLiterary;

    @ManyToOne
    @JoinColumn(name = "IdUser", nullable = false)
    @JsonIgnore
    private User user;

    @Column(name = "ISBN", length = 13)
    private String isbn;

    @Column(nullable = false)
    private Date yearPublishing;

    @Column(nullable = false)
    private Date createAt;

    @Column(nullable = false)
    private Date updateAt;

    @ManyToOne
    @JoinColumn(name = "IdStatus", nullable = false)
    private Status status;

    @ManyToMany
    @JoinTable(name="category_offerList",
            joinColumns=  @JoinColumn(name="offer_list_id"),
            inverseJoinColumns= @JoinColumn(name="category_id")
    )
    private List<Category> categories;

    @JsonIgnore
    private boolean archived;
}

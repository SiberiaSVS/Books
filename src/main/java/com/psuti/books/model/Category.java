package com.psuti.books.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 25)
    private String name;

    @ManyToOne
    @JoinColumn//(nullable = false)
    @JsonIgnore
    private Category idParent;

    @Column(nullable = false)
    @JsonIgnore
    private boolean multiSelect = false;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private List<OfferList> offerLists;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private List<WishList> wishLists;
}

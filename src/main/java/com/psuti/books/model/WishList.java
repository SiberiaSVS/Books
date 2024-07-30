package com.psuti.books.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IdUser", nullable = false)
    @JsonIgnore
    private User user;

    @Column(nullable = false)
    private Date createAt;

    @Column(nullable = false)
    private Date updateAt;

    @ManyToOne
    @JoinColumn(name = "IdStatus", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "IdUserAddress", nullable = false)
    private UserAddress userAddress;

    @ManyToMany
    @JoinTable(name="category_wishList",
            joinColumns=  @JoinColumn(name="wishlist_id"),
            inverseJoinColumns= @JoinColumn(name="category_id")
    )
    private List<Category> categories;

    @OneToOne
    @JsonIgnore
    private OfferList offerList;

    @JsonIgnore
    private boolean archived;
}

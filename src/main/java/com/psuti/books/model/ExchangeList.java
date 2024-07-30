package com.psuti.books.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ExchangeList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "IdOfferList1", nullable = false)
    private OfferList offerList1;

    @OneToOne
    @JoinColumn(name = "IdWishList1")
    private WishList wishList1;

    @OneToOne
    @JoinColumn(name = "IdOfferList2", nullable = false)
    private OfferList offerList2;

    @OneToOne
    @JoinColumn(name = "IdWishList2")
    private WishList wishList2;

    @Column(nullable = false)
    private Date createAt;

    @Column(nullable = false)
    private boolean isBoth = false;
}


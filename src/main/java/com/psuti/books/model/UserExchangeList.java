package com.psuti.books.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserExchangeList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "IdExchangeList", nullable = false)
    private ExchangeList exchangeList;

    @OneToOne
    @JoinColumn (name = "IdOfferList", nullable = false)
    private OfferList offerlist;

    @Column (length = 14)
    private String trackNumber;

    @Column (nullable = false)
    private boolean receiving = false;
}

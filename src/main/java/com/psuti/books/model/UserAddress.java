package com.psuti.books.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IdUser", nullable = false)
    @JsonIgnore
    private User user;

    @Column(nullable = false, length = 6)
    private String addrIndex;

    @Column(nullable = false, length = 15)
    private String addrCity;

    @Column(nullable = false, length = 25)
    private String addrStreet;

    @Column(nullable = false, length = 5)
    private String addrHouse;

    @Column(length = 10)
    private String AddrStructure;

    @Column(length = 3)
    private String AddrApart;

    private boolean isDefault = false;
}

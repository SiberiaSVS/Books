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
public class UserMsg {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "IdUser", nullable = false)
    private User user;

    @Column(nullable = false)
    private Date createAt;

    @Column(nullable = false, length = 250)
    private String text;

    @Column(length = 150)
    private String notes;

    @ManyToOne
    @JoinColumn(name = "IdStatus", nullable = false)
    private Status status;

    @Column (nullable = false)
    private int type;
}

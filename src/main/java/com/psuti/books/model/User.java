package com.psuti.books.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 25)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(length = 25)
    private String secondName;

    @Column(nullable = false, length = 15, unique = true)
    private String email;

    @Column(nullable = false, length = 20)
    private String userName;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private boolean enabled = true;

    @Lob
    private byte[] avatar;

    @Column(nullable = false)
    private String role;

//    @Column(nullable = false)
//    private boolean isStaff = false;
//
//    @Column(nullable = false)
//    private boolean isSuperAdmin = false;
}

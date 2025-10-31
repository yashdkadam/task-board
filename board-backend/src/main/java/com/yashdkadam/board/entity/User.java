package com.yashdkadam.board.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Users")
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(length = 50, unique = true, nullable = false)
    private String username;

    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @Column(length = 255, nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public enum Role {
        Admin,
        Developer,
        Tester
    }
}
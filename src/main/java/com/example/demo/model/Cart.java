package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @PrePersist
    void create() {
        createdAt = updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    void update() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
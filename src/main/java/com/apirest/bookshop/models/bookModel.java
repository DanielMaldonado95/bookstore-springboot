package com.apirest.bookshop.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "books")
public class bookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long bookId;
    @Column(nullable = false)
    private String title;
    private String description;
    @Column(nullable = false)
    private int stock;
    @Column(nullable = false)
    private float salePrice;
    private boolean available = true;
}

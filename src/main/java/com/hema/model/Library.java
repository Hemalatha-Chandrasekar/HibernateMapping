package com.hema.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity

public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="library_id")//Foreign key in the Book table


    private List<Book> books=new ArrayList<>();

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Library() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Library{" +
                "books=" + books +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

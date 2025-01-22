package com.hema.model;

import jakarta.persistence.*;

import javax.xml.namespace.QName;
import java.util.*;

@Entity

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name="library_id")//this is the foreign key in the member table
    private Library library;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address_id")
    private Address address;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinColumn(
//     name="member_book",
//     joinColumns=@JoinColumn(name="member_id"),
//    inverseJoinColumns=@JoinColumn(name="book_id"))
//    private Set<Book> books=new HashSet<>();
@ManyToMany(cascade = CascadeType.ALL)
@JoinTable(
        name = "member_book", // This is the name of the join table
        joinColumns = @JoinColumn(name = "member_id"), // Foreign key in the join table pointing to Member
        inverseJoinColumns = @JoinColumn(name = "book_id") // Foreign key in the join table pointing to Book
)
private Set<Book> books = new HashSet<>();

    public Member() {

    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public String getName() {
        return name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Member{" +
                "address=" + address +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", library=" + library +
                '}';
    }



}

package com.jpa.mssql.poc.demoJpawithMSSql.entity;

import com.jpa.mssql.poc.demoJpawithMSSql.Listener.PersistEntityListener;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners(
        PersistEntityListener.class
)
public class Item {

    private Long id;

    private String name;

    private LocalDate auction;

    @ManyToOne(fetch = FetchType.EAGER)
    private User seller;

    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "item", fetch = FetchType.EAGER)
    private Set<Bid> bids = new HashSet<>();

    public Item() {
    }

    public Item(String name, LocalDate auction, User seller) {
        this.name = name;
        this.auction = auction;
        this.seller = seller;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public LocalDate getAuction() {
        return auction;
    }

    public void setAuction(LocalDate auction) {
        this.auction = auction;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    @ManyToMany(mappedBy = "items")
    public Set<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @OneToMany(mappedBy = "item")
    @org.hibernate.annotations.LazyCollection(
            org.hibernate.annotations.LazyCollectionOption.EXTRA
    )
    public Set<Bid> getBids() {
        return bids;
    }

    public void addBid(Bid bid) {
        bids.add(bid);
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }
}


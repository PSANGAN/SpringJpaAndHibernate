package com.jpa.mssql.poc.demoJpawithMSSql.entity;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User bidder;

    @NotNull
    private BigDecimal amount;

    public Bid() {
    }

    public Bid(BigDecimal amount) {
        this.amount = amount;
    }

    public Bid(Item item, User bidder, BigDecimal amount) {
        this.item = item;
        this.bidder = bidder;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getBidder() {
        return bidder;
    }

    public void setBidder(User bidder) {
        this.bidder = bidder;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

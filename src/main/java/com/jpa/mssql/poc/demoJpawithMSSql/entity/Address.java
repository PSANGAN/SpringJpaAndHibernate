package com.jpa.mssql.poc.demoJpawithMSSql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "zip_code")
    private String zipCode;

    // Getters and setters
}


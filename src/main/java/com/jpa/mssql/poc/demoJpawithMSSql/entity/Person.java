package com.jpa.mssql.poc.demoJpawithMSSql.entity;

import jakarta.persistence.*;

@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "streetAddress", column = @Column(name = "person_street")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "person_zip"))
    })
    private Address personAddress;

    @Embedded
    private Address homeAddress;

}

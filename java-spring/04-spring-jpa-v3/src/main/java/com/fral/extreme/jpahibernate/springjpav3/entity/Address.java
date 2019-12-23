package com.fral.extreme.jpahibernate.springjpav3.entity;

import javax.persistence.Embeddable;

/**
 * Embeddable lets us store entity data in a single table. If this is contained for another one, its properties
 * will be stored in it.
 * */
@Embeddable
public class Address {

    private String line1;
    private String line2;
    private String city;

    protected Address() {
    }

    public Address(String line1, String line2, String city) {
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
    }
}

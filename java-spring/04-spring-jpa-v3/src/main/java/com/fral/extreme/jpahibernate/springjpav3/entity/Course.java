package com.fral.extreme.jpahibernate.springjpav3.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Course {

    //region Properties
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    //endregion

    //region Constructors
    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }
    //endregion

    //region Getters & Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion
}

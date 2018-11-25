 package com.fral.extreme.jpahibernate.springjpav3.entity;

import javax.persistence.*;

@Entity
public class Passport {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String number;

    //We need reference to Student for having bi-directional relationship, but Student
    //is going to be the owner of the relation, so we need to add mappedBy property.
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
    private Student student;


    public Passport() {
    }

    public Passport(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "number='" + number + '\'' +
                '}';
    }
}

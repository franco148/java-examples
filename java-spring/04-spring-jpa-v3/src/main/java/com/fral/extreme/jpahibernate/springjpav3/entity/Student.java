package com.fral.extreme.jpahibernate.springjpav3.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    // The default FetchType is EAGER. The entity loads its dependencies.
    // Choosing as the owner of the relationship
    @OneToOne(fetch = FetchType.LAZY)
    private Passport passport;

    //For this example, student is the owner of many to many relationships.
    @ManyToMany
    private List<Course> courses = new ArrayList<>();


    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}

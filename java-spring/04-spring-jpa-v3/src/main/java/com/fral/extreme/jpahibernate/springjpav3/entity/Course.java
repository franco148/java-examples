package com.fral.extreme.jpahibernate.springjpav3.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "CourseDetailsTable")

//@NamedQuery is not an repeatable query, so if we need to have more than one, we need to use, @NamedQueries
//@NamedQueries(value = { @NamedQuery(name = "query_get_all_courses", query = "SELECT c FROM Course c"), and others ....})
@NamedQuery(name = "query_get_all_courses", query = "SELECT c FROM Course c")
public class Course {

    //region Properties
    @Id
    @GeneratedValue
    private Long id;

//    @Column(name = "fullname", nullable = false)
    private String name;

    //This updates automatically every time an entity is updated.
    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp
    private LocalDateTime createdDate;

    //FetchType by default in OneToMany relationships are LAZY.
    @OneToMany(mappedBy = "course")
    private List<Review> reviews = new ArrayList<>();
    //endregion

    //region Constructors
    protected Course() {
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
    }

    //endregion


    //region Overrides
    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                '}';
    }
    //endregion
}

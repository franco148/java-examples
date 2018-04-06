package com.fral.extreme.jpahibernate.springjpav3.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//Another option is, not use inheritance instead a @MappedSuperClass
@MappedSuperclass

//@Entity

//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//The following for overriding the DType column which is the default.
//@DiscriminatorColumn(name = "")

//For retrieving data, it uses UNION.
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

//@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Employee {

    //region Properties
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    //endregion

    //region Constructors
    protected Employee() {
    }

    public Employee(String name) {
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


    //region Overrides
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                '}';
    }
    //endregion
}

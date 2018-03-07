package com.fral.extreme.sbjpa.springjpav2.jpa;

import com.fral.extreme.sbjpa.springjpav2.entity.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//Repository
@Repository
//Transaction
@Transactional
public class PersonJpaRepository {

    //Connect to the database
    @PersistenceContext
    EntityManager entityManager;


    public Person findById(int id) {
        return entityManager.find(Person.class, id);
    }
}

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

    /**
     * If a person with de specified ID already exists, this is updated based on changes,
     * but if it does not, then a new person is created.
     * @param person
     * @return
     */
    public Person update(Person person) {
        return entityManager.merge(person);
    }

    //This is not required, since the previous methods has the same behavior.
//    public Person insert(Person person) {
//        return entityManager.merge(person);
//    }

    public void delete(int id) {
        Person person = findById(id);
        entityManager.remove(person);
    }
}

package com.fral.extreme.jpahibernate.springjpav3.repository;

import com.fral.extreme.jpahibernate.springjpav3.entity.Course;
import com.fral.extreme.jpahibernate.springjpav3.entity.Passport;
import com.fral.extreme.jpahibernate.springjpav3.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
//Or specify the context that we want to test.
//@SpringBootTest(classes = SpringJpaV3Application.class)
public class StudentRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    public void retrieveStudentsAndPassportDetails() {

        Student student = entityManager.find(Student.class, 20001L);
        logger.info("Student -> {}", student);

        //The following sentence is going to fail because now the FETCH TYPE is LAZY
        //So to fix that, we need the @transactional annotation.
        logger.info("passport -> {}", student.getPassport());
    }


    /**
     * - Session & Session Factory
     * - EntityManager & Persistence Context
     * - Transaction.
     */
    @Test
    //@Transactional //Creates a persistence context: Is a place where all the entities where we are operating are being stored.
    public void someTest() {
        studentRepository.someOperationToUnderstandPersistenceContext();
    }

    @Test
    @Transactional
    public void retrievePassportAndAssociatedStudentDetails() {

        Passport passport = entityManager.find(Passport.class, 30001L);
        logger.info("Passport -> {}", passport);

        logger.info("Associated Student -> {}", passport.getStudent());
    }
}
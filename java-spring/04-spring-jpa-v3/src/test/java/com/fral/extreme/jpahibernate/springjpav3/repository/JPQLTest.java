package com.fral.extreme.jpahibernate.springjpav3.repository;

import com.fral.extreme.jpahibernate.springjpav3.entity.Course;
import com.fral.extreme.jpahibernate.springjpav3.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
//Or specify the context that we want to test.
//@SpringBootTest(classes = SpringJpaV3Application.class)
public class JPQLTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager entityManager;

    @Test
    public void jpqlQueries_basic() {

        Query query = entityManager.createQuery("SELECT c FROM Course c");
        List resultList = query.getResultList();
        logger.info("SELECT c FROM Course c -> {}", resultList);
    }

    @Test
    public void jpqlQueries_typed() {

        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("SELECT c FROM Course c -> {}", resultList);
    }

    @Test
    public void jpqlQueries_named() {

        Query query = entityManager.createNamedQuery("query_get_all_courses");
        List resultList = query.getResultList();
        logger.info("SELECT c FROM Course c -> {}", resultList);
    }

    @Test
    public void jpql_courses_without_students() {
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c WHERE c.students is empty", Course.class);
        List<Course> resultList = query.getResultList();

        logger.info("Result of JPQL Courses without Students -> {}", resultList);
    }

    @Test
    public void jpql_courses_with_atleast_2_students() {
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c WHERE SIZE(c.students) >= 2", Course.class);
        List<Course> resultList = query.getResultList();

        logger.info("Result of JPQL Courses without Students -> {}", resultList);
    }

    @Test
    public void jpql_courses_ordered_by_numberOf_students() {
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c ORDER BY SIZE(c.students)", Course.class);
        List<Course> resultList = query.getResultList();

        logger.info("Result of JPQL Courses without Students -> {}", resultList);
    }

    @Test
    public void jpql_students_with_passports_in_a_certain_pattern() {
        TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s WHERE s.passport.number LIKE '%343%'", Student.class);
        List<Student> resultList = query.getResultList();

        logger.info("Result of JPQL Students -> {}", resultList);
    }


    //JOIN => SELECT c, s FROM Course c JOIN c.students s
    //LEFT JOIN => SELECT c, s FROM Course c LEFT JOIN c.students s
    //CROSS JOIN => SELECT c, s FROM Course c, students s
    @Test
    public void join_test() {
        Query query = entityManager.createQuery("SELECT c, s FROM Course c JOIN c.students s");
        List<Object[]> resultList = query.getResultList();

        logger.info("Result of JPQL Students -> {}", resultList.size());

        for (Object[] result : resultList) {
            //result[0];//Course
            //result[1];//Student
            logger.info("Course{} and Student{}", result[0], result[1]);
        }
    }

    @Test
    public void left_join_test() {
        Query query = entityManager.createQuery("SELECT c, s FROM Course c LEFT JOIN c.students s");
        List<Object[]> resultList = query.getResultList();

        logger.info("Result of JPQL Students -> {}", resultList.size());

        for (Object[] result : resultList) {
            //result[0];//Course
            //result[1];//Student
            logger.info("Course{} and Student {}", result[0], result[1]);
        }
    }

    @Test
    public void cross_join_test() {
        Query query = entityManager.createQuery("SELECT c, s FROM Course c, Student s");
        List<Object[]> resultList = query.getResultList();

        logger.info("Result of JPQL Students -> {}", resultList.size());

        for (Object[] result : resultList) {
            //result[0];//Course
            //result[1];//Student
            logger.info("Course{} and Student {}", result[0], result[1]);
        }
    }
}
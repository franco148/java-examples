package com.fral.extreme.jpahibernate.springjpav3.repository;

import com.fral.extreme.jpahibernate.springjpav3.entity.Course;
import com.fral.extreme.jpahibernate.springjpav3.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
//Or specify the context that we want to test.
//@SpringBootTest(classes = SpringJpaV3Application.class)
public class CriteriaQueriesTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager entityManager;


    @Test
    public void jpqlQueries_named() {

        //Select c From Course c

        //1. Use Criteria Builder to create a Criteria Query returning the expected result object.
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cQuery = cBuilder.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query.
        Root<Course> courseRoot = cQuery.from(Course.class);

        //3. Define predicates etc using Criteria Builder
        //4. Add predicates etc to the Criteria Query
        //5. Build the TypedQuery using the entity manager and criteria query.

        TypedQuery<Course> query = entityManager.createQuery(cQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("SELECT c FROM Course c -> {}", resultList);
    }

    @Test
    public void all_courses_having_100Steps() {

        //Select c From Course c Where name like '%100 Steps%'

        //1. Use Criteria Builder to create a Criteria Query returning the expected result object.
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cQuery = cBuilder.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query.
        Root<Course> courseRoot = cQuery.from(Course.class);

        //3. Define predicates etc using Criteria Builder
        Predicate like100Steps = cBuilder.like(courseRoot.get("name"), "%100 steps");

        //4. Add predicates etc to the Criteria Query
        cQuery.where(like100Steps);

        //5. Build the TypedQuery using the entity manager and criteria query.

        TypedQuery<Course> query = entityManager.createQuery(cQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("SELECT c FROM Course c -> {}", resultList);
    }

    @Test
    public void all_courses_without_students() {

        //Select c From Course c Where c.students is empty

        //1. Use Criteria Builder to create a Criteria Query returning the expected result object.
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cQuery = cBuilder.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query.
        Root<Course> courseRoot = cQuery.from(Course.class);

        //3. Define predicates etc using Criteria Builder
        Predicate studentsIsEmpty = cBuilder.isEmpty(courseRoot.get("students"));

        //4. Add predicates etc to the Criteria Query
        cQuery.where(studentsIsEmpty);

        //5. Build the TypedQuery using the entity manager and criteria query.

        TypedQuery<Course> query = entityManager.createQuery(cQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("SELECT c FROM Course c -> {}", resultList);
    }


    @Test
    public void join_courses_and_students() {

        //Select c From Course c Join c.students s

        //1. Use Criteria Builder to create a Criteria Query returning the expected result object.
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cQuery = cBuilder.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query.
        Root<Course> courseRoot = cQuery.from(Course.class);

        //3. Define predicates etc using Criteria Builder
        Join<Object, Object> join = courseRoot.join("students");

        //4. Add predicates etc to the Criteria Query


        //5. Build the TypedQuery using the entity manager and criteria query.

        TypedQuery<Course> query = entityManager.createQuery(cQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("SELECT c FROM Course c -> {}", resultList);
    }


    @Test
    public void left_join_courses_and_students() {

        //Select c From Course c Join c.students s

        //1. Use Criteria Builder to create a Criteria Query returning the expected result object.
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cQuery = cBuilder.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query.
        Root<Course> courseRoot = cQuery.from(Course.class);

        //3. Define predicates etc using Criteria Builder
        Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);

        //4. Add predicates etc to the Criteria Query


        //5. Build the TypedQuery using the entity manager and criteria query.

        TypedQuery<Course> query = entityManager.createQuery(cQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("SELECT c FROM Course c -> {}", resultList);
    }

}
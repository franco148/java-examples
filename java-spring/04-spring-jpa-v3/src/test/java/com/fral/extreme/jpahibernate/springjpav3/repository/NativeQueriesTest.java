package com.fral.extreme.jpahibernate.springjpav3.repository;

import com.fral.extreme.jpahibernate.springjpav3.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NativeQueriesTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager entityManager;

    @Test
    public void nativeQueries_basic() {

        //Once I have updated my Course Entity for Soft Delete operations, a solution for Native queries would
        //be update the query adding where clause since it does not support annotation.
        Query query = entityManager.createNativeQuery("SELECT * FROM Course c");
        List resultList = query.getResultList();
        logger.info("SELECT c FROM Course c -> {}", resultList);
    }

    @Test
    public void nativeQueries_with_parameter() {

        Query query = entityManager.createNativeQuery("SELECT * FROM Course WHERE id = ?", Course.class);
        query.setParameter(1, 10001L);
        List resultList = query.getResultList();
        logger.info("SELECT c FROM Course c -> {}", resultList);
    }

    @Test
    public void nativeQueries_with_named_parameter() {

        Query query = entityManager.createNativeQuery("SELECT * FROM Course WHERE id = :id", Course.class);
        query.setParameter("id", 10001L);
        List resultList = query.getResultList();
        logger.info("SELECT c FROM Course c -> {}", resultList);
    }

    @Test
    @Transactional
    public void nativeQueries_to_update() {

        Query query = entityManager.createNativeQuery("UPDATE COURSE SET last_updated_date = sysdate()", Course.class);

        int noOfRowsUpdated = query.executeUpdate();
        logger.info("SELECT c FROM Course c -> {}", noOfRowsUpdated);
    }
}
package com.fral.extreme.jpahibernate.springjpav3.repository;

import com.fral.extreme.jpahibernate.springjpav3.entity.Course;
import com.fral.extreme.jpahibernate.springjpav3.entity.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PerformanceTuningTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    public void creatingNPlusOneProblem() {

        List<Course> courses = entityManager.createNamedQuery("query_get_all_courses", Course.class).getResultList();

        //logger.info("Testing is running...");
        for (Course course : courses) {
            logger.info("Course -> {} Students -> {}", course, course.getStudents());
        }
    }


    @Test
    @Transactional
    public void solvingNPlusOneProblem_EntityGraph() {

        EntityGraph<Course> entityGraph = entityManager.createEntityGraph(Course.class);
        Subgraph<Object> subgraph = entityGraph.addSubgraph("students");

        List<Course> courses = entityManager
                .createNamedQuery("query_get_all_courses", Course.class)
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();

        //logger.info("Testing is running...");
        for (Course course : courses) {
            logger.info("Course -> {} Students -> {}", course, course.getStudents());
        }
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblem_JoinFetch() {

        List<Course> courses = entityManager.createNamedQuery("query_get_all_courses_join_fetch", Course.class).getResultList();

        //logger.info("Testing is running...");
        for (Course course : courses) {
            logger.info("Course -> {} Students -> {}", course, course.getStudents());
        }
    }

}
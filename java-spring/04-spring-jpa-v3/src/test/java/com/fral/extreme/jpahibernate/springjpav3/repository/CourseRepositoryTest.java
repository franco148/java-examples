package com.fral.extreme.jpahibernate.springjpav3.repository;

import com.fral.extreme.jpahibernate.springjpav3.SpringJpaV3Application;
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

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
//Or specify the context that we want to test.
//@SpringBootTest(classes = SpringJpaV3Application.class)
public class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void findById_basic() {

        Course course = courseRepository.findById(10001L);

        assertEquals("JPA in 50 steps", course.getName());

        //logger.info("Testing is running...");
    }

    @Test
    @DirtiesContext
    public void save_basic() {
        // Get a course
        Course course = courseRepository.findById(10001L);
        assertEquals("JPA in 50 steps", course.getName());

        // Updating details of the course
        course.setName(course.getName() + " - Updated");
        courseRepository.save(course);

        // Check the value
        Course updatedCourse = courseRepository.findById(10001L);
        assertEquals("JPA in 50 steps - Updated", updatedCourse.getName());
    }

    @Test
    @DirtiesContext
    public void deleteById_basic() {
        courseRepository.deleteById(10002L);

        assertNull(courseRepository.findById(10002L));
    }

    @Test
    @Transactional
    public void retrieveReviewsForCourse() {
        Course course = courseRepository.findById(10001L);
        logger.info("{}", course.getReviews());
    }

    @Test
    public void retrieveCourseForReview() {
        Review review = entityManager.find(Review.class, 40001L);
        logger.info("{}", review.getCourse());

    }
}
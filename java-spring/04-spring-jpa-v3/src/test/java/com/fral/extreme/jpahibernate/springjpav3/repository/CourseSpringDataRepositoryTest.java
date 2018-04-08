package com.fral.extreme.jpahibernate.springjpav3.repository;

import com.fral.extreme.jpahibernate.springjpav3.entity.Course;
import com.fral.extreme.jpahibernate.springjpav3.entity.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
//Or specify the context that we want to test.
//@SpringBootTest(classes = SpringJpaV3Application.class)
public class CourseSpringDataRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseSpringDataRepository courseRepository;


    @Test
    public void findById_basic() {

        Optional<Course> course = courseRepository.findById(10001L);

        logger.info("Course exists? -> {}", course.isPresent());
    }

    @Test
    public void playingAroundWithSpringDataRepository() {
        Course course = new Course("Microservices eBook");
        courseRepository.save(course);

        course.setName("Microservices eBook v1.0");
        courseRepository.save(course);

        logger.info("Find all courses -> {}", courseRepository.findAll());
        logger.info("Courses count -> {}", courseRepository.count());
    }

    @Test
    public void get_sorted_courses() {

        //Defining SORT criteria
        Sort sort = new Sort(Sort.Direction.DESC, "name");

        logger.info("Find all courses sorted by name DESC -> {}", courseRepository.findAll(sort));
    }

}
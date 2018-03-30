package com.fral.extreme.jpahibernate.springjpav3.repository;

import com.fral.extreme.jpahibernate.springjpav3.SpringJpaV3Application;
import com.fral.extreme.jpahibernate.springjpav3.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
//Or specify the context that we want to test.
//@SpringBootTest(classes = SpringJpaV3Application.class)
public class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void findById_basic() {

        Course course = courseRepository.findById(10001L);

        assertEquals("JPA in 50 steps", course.getName());

        //logger.info("Testing is running...");
    }

    @Test
    public void save() {
    }

    @Test
    public void deleteById_basic() {
        courseRepository.deleteById(10002L);

        assertNull(courseRepository.findById(10002L));
    }
}
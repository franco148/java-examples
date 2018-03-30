package com.fral.extreme.jpahibernate.springjpav3.repository;

import com.fral.extreme.jpahibernate.springjpav3.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class CourseRepository {

    @Autowired
    private EntityManager entityManager;

    public Course findById(Long id) {

        return entityManager.find(Course.class, id);
    }

    // Insert or update
    public Course save(Course course) {
        return null;
    }

    public void deleteById(Long id) {

        Course course = findById(id);
        entityManager.remove(course);
    }
}

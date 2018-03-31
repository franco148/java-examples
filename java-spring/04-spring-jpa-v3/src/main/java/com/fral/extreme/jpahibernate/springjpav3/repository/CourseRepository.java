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

        if (course.getId() == null) {
            entityManager.persist(course);
        } else {
            entityManager.merge(course);
        }

        return course;
    }

    public void deleteById(Long id) {

        Course course = findById(id);
        entityManager.remove(course);
    }

    public void playWithEntityManager() {
        Course course1 = new Course("Micro services eBook");
        entityManager.persist(course1);
        entityManager.flush();

        // Transaction keeps tracking the following changes
        course1.setName("Micro services eBook - Updated");
        entityManager.flush();

        // -----------------------------------------------
        Course course2 = new Course("Learn ReacJs framework");
        entityManager.persist(course2);
        entityManager.flush();

        //To not track the changes any more or use entityManager.clear(); for all.
        entityManager.detach(course2);
        // Transaction keeps tracking the following changes
        course2.setName("Learn ReacJs framework - Updated");
        entityManager.flush();
    }
}

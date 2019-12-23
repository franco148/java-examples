package com.fral.extreme.jpahibernate.springjpav3.repository;

import com.fral.extreme.jpahibernate.springjpav3.entity.Course;
import com.fral.extreme.jpahibernate.springjpav3.entity.Review;
import com.fral.extreme.jpahibernate.springjpav3.entity.ReviewRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
        //Another way to avoid save post operations we can use.
        //entityManager.refresh(course2); //It is going to take the information that is already saved in the database.
        entityManager.detach(course2);
        // Transaction keeps tracking the following changes
        course2.setName("Learn ReacJs framework - Updated");
        entityManager.flush();
    }

    public void addReviewForCourse() {
        //get the course 10003
        Course course10003 = findById(10003L);
        logger.info("course.getReviews() -> {}", course10003.getReviews());

        //add 2 reviews to it
        Review review1 = new Review(ReviewRating.FIVE, "Great Hands-on Stuff.");
        Review review2 = new Review(ReviewRating.FIVE, "Really helpful.");

        //setting relationships
        course10003.addReview(review1);
        review1.setCourse(course10003);

        course10003.addReview(review2);
        review2.setCourse(course10003);

        //save it to the database
        entityManager.persist(review1);
        entityManager.persist(review2);
    }

    public void addReviewsForCourse(Long courseId, List<Review> reviews) {
        //get the course 10003
        Course course10003 = findById(courseId);
        logger.info("course.getReviews() -> {}", course10003.getReviews());

        for (Review rev : reviews) {

            //setting relationships
            course10003.addReview(rev);
            rev.setCourse(course10003);

            //save it to the database
            entityManager.persist(rev);
        }
    }
}

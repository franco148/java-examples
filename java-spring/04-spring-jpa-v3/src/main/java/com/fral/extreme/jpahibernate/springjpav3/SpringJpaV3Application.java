package com.fral.extreme.jpahibernate.springjpav3;

import com.fral.extreme.jpahibernate.springjpav3.entity.Course;
import com.fral.extreme.jpahibernate.springjpav3.entity.Review;
import com.fral.extreme.jpahibernate.springjpav3.repository.CourseRepository;
import com.fral.extreme.jpahibernate.springjpav3.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringJpaV3Application implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaV3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//	    Course courseResponse = courseRepository.findById(10001L);
//	    logger.info("Course 10001 -> {}", courseResponse);

//	    courseRepository.deleteById(10001L);

//        courseRepository.save(new Course("Advanced Micro services creating an application"));

        //courseRepository.playWithEntityManager();

		//studentRepository.saveStudentWithPassport();

		//courseRepository.addReviewForCourse();

		List<Review> reviews = new ArrayList<>();
		Review review1 = new Review("5", "Great Hands-on Stuff.");
		Review review2 = new Review("5", "Really helpful.");

		reviews.add(review1);
		reviews.add(review2);

		courseRepository.addReviewsForCourse(10003L, reviews);
	}
}

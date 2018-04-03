package com.fral.extreme.jpahibernate.springjpav3;

import com.fral.extreme.jpahibernate.springjpav3.entity.Course;
import com.fral.extreme.jpahibernate.springjpav3.repository.CourseRepository;
import com.fral.extreme.jpahibernate.springjpav3.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

		studentRepository.saveStudentWithPassport();
	}
}

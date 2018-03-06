package com.fral.extreme.sbjpa.springjpav2;

import com.fral.extreme.sbjpa.springjpav2.entity.Person;
import com.fral.extreme.sbjpa.springjpav2.jdbc.PersonJdbcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

/**
 * CommandLineRunner: the implementation is lunch as soon as the application is ready.
 *
 * Repos:
 * - HibernateJPAStepByStep
 *
 */
@SpringBootApplication
public class SpringJpaV2Application implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PersonJdbcDao dao;

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaV2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("All users -> {}", dao.findAll());
		logger.info("Users id 1003 -> {}", dao.findById(1003));
		logger.info("Deleting id 1001 -> Number of rows deleted - {}", dao.deleteById(1001));

		logger.info("Inserting id 1004 -> {}", dao.save(new Person(1004, "Marco Cardenas", "Sucre-Bolivia", new Date())));
		logger.info("Updating id 1002 -> {}", dao.udpate(new Person(1002, "Fernando Arratia", "Cocha-Bolivia", new Date())));
	}
}

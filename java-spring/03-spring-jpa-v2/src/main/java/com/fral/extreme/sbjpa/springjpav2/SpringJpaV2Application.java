package com.fral.extreme.sbjpa.springjpav2;

import com.fral.extreme.sbjpa.springjpav2.jdbc.PersonJdbcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CommandLineRunner: the implementation is lunch as soon as the application is ready.
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
	}
}

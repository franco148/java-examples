package com.fral.extreme.sbjpa.springjpav2.jdbc;

import com.fral.extreme.sbjpa.springjpav2.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonJdbcDao {

    /**
     * We definitely need a connection string for connecting to the database, but
     * this should be handle by spring. So we need to use in this case for JDBC
     * JdbcTemplate as follows:
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    //Select * from person
    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper(Person.class));
    }
}

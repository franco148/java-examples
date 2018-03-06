package com.fral.extreme.sbjpa.springjpav2.jdbc;

import com.fral.extreme.sbjpa.springjpav2.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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

    public Person findById(int id) {
        return jdbcTemplate.queryForObject("select * from person where id=?",
                                            new Object[]{id},
                                            new BeanPropertyRowMapper<Person>(Person.class));
    }

    //This as a result returns how many rows were affected with the query.
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE from person where id=?", new Object[]{id});
    }

    public int save(Person person) {
        return jdbcTemplate.update("INSERT INTO PERSON (id, name, location, birth_date) VALUES (?, ?, ?, ?)",
                                   new Object[] {
                                        person.getId(),
                                        person.getName(),
                                        person.getLocation(),
                                        new Timestamp(person.getBirthDate().getTime())
        });
    }

    public int udpate(Person person) {
        return jdbcTemplate.update("UPDATE PERSON " +
                                        "SET name=?, location=?, birth_date=?" +
                                        "WHERE id=?",
                new Object[] {
                        person.getName(),
                        person.getLocation(),
                        new Timestamp(person.getBirthDate().getTime()),
                        person.getId()
                });
    }
}

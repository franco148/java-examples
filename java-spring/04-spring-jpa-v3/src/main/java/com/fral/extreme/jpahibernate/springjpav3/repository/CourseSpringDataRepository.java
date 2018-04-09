package com.fral.extreme.jpahibernate.springjpav3.repository;

import com.fral.extreme.jpahibernate.springjpav3.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

    List<Course> findByName(String name);
    List<Course> findByIdDesc(String name);
    List<Course> countByName(String name);

    @Query("SELECT c FROM Course c WHERE name LIKE  '%100 steps'")
    List<Course> coursesWith100Steps(String name);

    @Query(value = "SELECT c FROM Course c WHERE name LIKE  '%100 steps'", nativeQuery = true)
    List<Course> coursesWith100StepsUsingNativeQuery(String name);

    @Query("query_get_all_courses")
    List<Course> coursesWith100StepsInNaveUsingNamedQuery(String name);
}

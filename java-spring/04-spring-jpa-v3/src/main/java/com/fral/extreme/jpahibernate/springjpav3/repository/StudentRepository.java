package com.fral.extreme.jpahibernate.springjpav3.repository;

import com.fral.extreme.jpahibernate.springjpav3.entity.Course;
import com.fral.extreme.jpahibernate.springjpav3.entity.Passport;
import com.fral.extreme.jpahibernate.springjpav3.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class StudentRepository {

    @Autowired
    private EntityManager entityManager;

    public Student findById(Long id) {

        return entityManager.find(Student.class, id);
    }

    // Insert or update
    public Student save(Student student) {

        if (student.getId() == null) {
            entityManager.persist(student);
        } else {
            entityManager.merge(student);
        }

        return student;
    }

    public void deleteById(Long id) {

        Student course = findById(id);
        entityManager.remove(course);
    }

    public void saveStudentWithPassport() {
        Passport passport = new Passport("Z123456");
        entityManager.persist(passport);


        Student student = new Student("John Smith");
        student.setPassport(passport);
        entityManager.persist(student);
    }

    public void playWithEntityManager() {
        Student student1 = new Student("Micro services eBook");
        entityManager.persist(student1);
        entityManager.flush();

        // Transaction keeps tracking the following changes
        student1.setName("Micro services eBook - Updated");
        entityManager.flush();

        // -----------------------------------------------
        Student student2 = new Student("Learn ReacJs framework");
        entityManager.persist(student2);
        entityManager.flush();

        //To not track the changes any more or use entityManager.clear(); for all.
        //Another way to avoid save post operations we can use.
        //entityManager.refresh(course2); //It is going to take the information that is already saved in the database.
        entityManager.detach(student2);
        // Transaction keeps tracking the following changes
        student2.setName("Learn ReacJs framework - Updated");
        entityManager.flush();
    }

    public void someOperationToUnderstandPersistenceContext() {

        //Database Operation 1 - Retrieve student
        Student student = entityManager.find(Student.class, 20001L);
        //PersistenceContext(student)

        //Database Operation 2 - Retrieve passport
        Passport passport = student.getPassport();
        //PersistenceContext(student, passport)

        //Database Operation 3 - Update passport
        passport.setNumber("ZA654321");
        //PersistenceContext(student, passport++)

        //Database Operation 4 - Update student
        student.setName("Franco Name - Updated");
        //PersistenceContext(student++, passport++)
    }

    public void insertStudentAndCourse() {
        Student student = new Student("Jack Sparrow");
        Course course = new Course("Como ser un buen pirata");

        entityManager.persist(student);
        entityManager.persist(course);

        student.addCourse(course);
        course.addStudent(student);

        //Then save the owning side.
        entityManager.persist(student);
    }
}

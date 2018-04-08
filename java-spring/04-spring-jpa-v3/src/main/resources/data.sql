INSERT INTO course(id, name, created_date, last_updated_date)
VALUES (10001, 'JPA in 100 steps', sysdate(), sysdate());

INSERT INTO course(id, name, created_date, last_updated_date)
VALUES (10002, 'Spring in 50 steps', sysdate(), sysdate());

INSERT INTO course(id, name, created_date, last_updated_date)
VALUES (10003, 'Spring Framework in 50 steps', sysdate(), sysdate());

INSERT INTO course(id, name, created_date, last_updated_date)
VALUES (10004, 'Angular in 50 steps', sysdate(), sysdate());

INSERT INTO course(id, name, created_date, last_updated_date)
VALUES (10005, 'Angular JS in action', sysdate(), sysdate());

INSERT INTO course(id, name, created_date, last_updated_date)
VALUES (10006, 'Docker for developers', sysdate(), sysdate());

INSERT INTO course(id, name, created_date, last_updated_date)
VALUES (10007, 'Machine learning for beginners', sysdate(), sysdate());

INSERT INTO course(id, name, created_date, last_updated_date)
VALUES (10008, 'Mean stack development', sysdate(), sysdate());

INSERT INTO course(id, name, created_date, last_updated_date)
VALUES (10009, 'Psicologia del color', sysdate(), sysdate());

INSERT INTO course(id, name, created_date, last_updated_date)
VALUES (10010, 'ReactJS for dummies', sysdate(), sysdate());


-- --------------------------- PASSPORT -----------------------------------
INSERT INTO passport (id, number)
VALUES (30001, 'BO3343456');

INSERT INTO passport (id, number)
VALUES (30002, 'BO0098343');

INSERT INTO passport (id, number)
VALUES (30003, 'BO3309898');

-- --------------------------- STUDENT -----------------------------------
INSERT INTO student (id, name, passport_id)
VALUES (20001, 'Franco Arratia', 30001);

INSERT INTO student (id, name, passport_id)
VALUES (20002, 'Alvaro Gutierrez', 30002);

INSERT INTO student (id, name, passport_id)
VALUES (20003, 'Carlos Medinacelli', 30003);


-- --------------------------- REVIEW -----------------------------------
INSERT INTO review (id, rating, description, course_id)
VALUES (40001, '5', 'Great Course', 10001);

INSERT INTO review (id, rating, description, course_id)
VALUES (40002, '4', 'Awesome course', 10001);

INSERT INTO review (id, rating, description, course_id)
VALUES (40003, '5', 'Wonderful course', 10003);

-- --------------------------- STUDENTS IN COURSES -----------------------------------
INSERT INTO students_in_courses (student_id, course_id)
VALUES (20001, 10001);

INSERT INTO students_in_courses (student_id, course_id)
VALUES (20002, 10001);

INSERT INTO students_in_courses (student_id, course_id)
VALUES (20003, 10001);

INSERT INTO students_in_courses (student_id, course_id)
VALUES (20001, 10003);
INSERT INTO course(id, name, created_date, last_updated_date)
VALUES (10001, 'JPA in 50 steps', sysdate(), sysdate());

INSERT INTO course(id, name, created_date, last_updated_date)
VALUES (10002, 'Spring in 50 steps', sysdate(), sysdate());

INSERT INTO course(id, name, created_date, last_updated_date)
VALUES (10003, 'Spring Framework in 50 steps', sysdate(), sysdate());

INSERT INTO course(id, name, created_date, last_updated_date)
VALUES (10004, 'Angular in 50 steps', sysdate(), sysdate());


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
INSERT INTO review (id, rating, description)
VALUES (40001, '5', 'Great Course');

INSERT INTO review (id, rating, description)
VALUES (40002, '4', 'Awesome course');

INSERT INTO review (id, rating, description)
VALUES (40003, '5', 'Wonderful course');
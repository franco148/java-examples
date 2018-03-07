/**
* Database creation is not necessary when we are using JPA.
*/
-- CREATE TABLE person
-- (
-- 	id integer not null,
-- 	name varchar(255) not null,
-- 	location varchar(255),
-- 	birth_date timestamp,
--
-- 	primary key(id)
-- );

INSERT INTO PERSON (id, name, location, birth_date)
VALUES
  (1001, 'Franco Arratia', 'Cochabamba-Bolivia', SYSDATE()),
  (1002, 'Fernando Arratia', 'SantaCruz-Bolivia', SYSDATE()),
  (1003, 'Mireya Arratia', 'LaPaz-Bolivia', SYSDATE());